package com.reallyfun.server.service;

import com.reallyfun.server.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagServiceTests {
    @Autowired
    private ITagService iTagService;
    @Test
    public void insertTag() {
//        try {
//            String content = "冒险";
//            iTagService.insert(content);
//            System.out.println("添加成功！");
//        } catch (ServiceException e) {
//            System.out.println("添加失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
    }
    @Test
    public void deleteTag() {
//        try{
//            Integer id = 17;
//            iTagService.delete(id);
//            System.out.println("删除成功！");
//        } catch (ServiceException e) {
//            System.out.println("删除失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
    }
    @Test
    public void bindGameTag() {
//        try {
//            Integer gameId = 1;
//            Integer tagId = 2;
//            iTagService.bind(gameId,tagId);
//            System.out.println("绑定成功！");
//        } catch (ServiceException e) {
//            System.out.println("绑定失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
    }
    @Test
    public void unbindGameTag() {
//        try {
//            Integer gameId = 1;
//            Integer tagId = 1;
//            iTagService.unbind(gameId,tagId);
//            System.out.println("解除绑定成功！");
//        } catch (ServiceException e) {
//            System.out.println("解除绑定失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
    }
}