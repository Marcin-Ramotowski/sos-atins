package pl.atins.sos.data.dao.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringTest {

    @Autowired
    ApplicationContext context;

    @Test
    @Order(0)
    void contextLoads() {
        assertNotNull(context);
    }
}
