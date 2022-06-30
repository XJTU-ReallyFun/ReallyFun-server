package com.reallyfun.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ReallyFunServerApplicationTests {
    @Autowired(required = false)
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void getConnection() throws Exception {
        System.out.println(dataSource.getConnection());
    }
}
