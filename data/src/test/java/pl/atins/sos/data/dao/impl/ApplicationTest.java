package pl.atins.sos.data.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTest extends BaseIntegrationTest {

    @Autowired
    protected ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }
}
