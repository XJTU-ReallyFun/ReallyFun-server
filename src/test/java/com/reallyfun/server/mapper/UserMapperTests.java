package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {
    @Autowired(required = false)
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("testname");
        user.setPassword("encryptedPassword");
        user.setSalt("salt");
        user.setAuth(0);
        user.createBy(-1);
        Integer result = userMapper.insert(user);
        assert result == 1;
    }
}
