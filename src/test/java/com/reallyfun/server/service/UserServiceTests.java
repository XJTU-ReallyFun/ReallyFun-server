package com.reallyfun.server.service;

import com.reallyfun.server.BaseTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests extends BaseTests {
    @Autowired
    IUserService userService;

    @Test
    public void register() {
        for (int i = 0; i < 100; i++) {
            String name = randstr();
            String password = "12345678";
            String email = randstr() + "@email.com";
            userService.register(name, password, email);
        }
    }
}
