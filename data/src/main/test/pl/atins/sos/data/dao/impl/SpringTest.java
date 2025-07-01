package pl.atins.sos.data.dao.impl;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.atins.sos.data.dao.CrudDao;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class SpringTest {

    @Autowired
    ApplicationContext context;

    @Test
    @Order(0)
    void contextLoads() {
        assertNotNull(context);
    }
}
