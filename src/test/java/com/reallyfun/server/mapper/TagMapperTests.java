package com.reallyfun.server.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TagMapperTests {
    @Autowired(required = false)
    private TagMapper tagMapper;

    @Test
    public void insertTag(){
        String content = "动作";
        Integer rows = tagMapper.insertTag(content);
        System.out.println("rows=" + rows);
    }
    @Test
    public void deleteTag(){
        Integer id = 14;
        Integer rows = tagMapper.deleteTag(id);
        System.out.println("rows=" + rows);
    }
    @Test
    public void bindGameTag(){
        Integer gameId = 1;
        Integer tagId = 2;
        Integer rows = tagMapper.bindGameTag(gameId, tagId);
        System.out.println("rows=" + rows);
    }
    @Test
    public void unbindGameTag(){
        Integer gameId = 1;
        Integer tagId = 1;
        Integer rows = tagMapper.unbindGameTag(gameId,tagId);
        System.out.println("rows=" + rows);
    }
}
