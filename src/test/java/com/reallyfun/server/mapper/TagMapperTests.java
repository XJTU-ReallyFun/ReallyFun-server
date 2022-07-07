package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
        Date now = new Date();
        RelGameTag relgametag = new RelGameTag();
        relgametag.setGameId(1);
        relgametag.setTagId(1);
        relgametag.setModifiedTime(now);
        relgametag.setModifiedUser(1);
        relgametag.setCreatedTime(now);
        relgametag.setCreatedUser(1);
        Integer rows = tagMapper.bindGameTag(relgametag);
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
