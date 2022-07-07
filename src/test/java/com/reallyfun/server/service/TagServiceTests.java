package com.reallyfun.server.service;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;
import com.reallyfun.server.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TagServiceTests {
    @Autowired
    private ITagService iTagService;
    @Test
    public void insertTag() {
        try {
            String content = "冒险";
            iTagService.insertTag(content);
            System.out.println("添加成功！");
        } catch (ServiceException e) {
            System.out.println("添加失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void deleteTag() {
        try{
            Integer id = 17;
            iTagService.deleteTag(id);
            System.out.println("删除成功！");
        } catch (ServiceException e) {
            System.out.println("删除失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void bindGameTag() {
        try {
            Date now = new Date();
            RelGameTag relgametag = new RelGameTag();
            relgametag.setGameId(1);
            relgametag.setTagId(1);
            relgametag.setModifiedTime(now);
            relgametag.setModifiedUser(1);
            relgametag.setCreatedTime(now);
            relgametag.setCreatedUser(1);
            iTagService.bindGameTag(relgametag);
            System.out.println("绑定成功！");
        } catch (ServiceException e) {
            System.out.println("绑定失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void unbindGameTag() {
        try {
            Integer gameId = 1;
            Integer tagId = 1;
            iTagService.unbindGameTag(gameId,tagId);
            System.out.println("解除绑定成功！");
        } catch (ServiceException e) {
            System.out.println("解除绑定失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}