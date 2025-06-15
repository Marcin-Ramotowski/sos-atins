package pl.atins.sos.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Component
public class DataInitializer implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        runSqlFile("/sql/create-db.sql");
        runSqlFile("/sql/insert-data.sql");
    }

    public void runSqlFile(String pathInClasspath) {
        Resource resource = new ClassPathResource(pathInClasspath);
        try (Connection conn = dataSource.getConnection()) {
            String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            Arrays.stream(sql.split(";"))
                    .filter(s -> !s.isBlank())
                    .forEach(stmt -> {
                        try (Statement statement = conn.createStatement()) {
                            LOGGER.info("Executing SQL statement from {}: {}", pathInClasspath, stmt);
                            statement.execute(stmt + ";");
                        } catch (SQLException e) {
                            LOGGER.error("Error running SQL statement", e);
                        }
                    });
        } catch (SQLException | IOException e) {
            LOGGER.error("Error running SQL file {}", pathInClasspath, e);
        }
    }
}

