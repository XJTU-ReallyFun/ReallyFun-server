package com.reallyfun.server.service;

import com.reallyfun.server.entity.Example;
import com.reallyfun.server.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExampleServiceTests {
    @Autowired
    private IExampleService iExampleService;

    @Test
    public void add() {
        try {
            iExampleService.add(123, "456");
            System.out.println("成功！");
        } catch (ServiceException e) {
            System.out.println("失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}