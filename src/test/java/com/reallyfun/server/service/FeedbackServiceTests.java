package com.reallyfun.server.service;

import com.reallyfun.server.entity.Feedback;
import com.reallyfun.server.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FeedbackServiceTests {
//    @Autowired
//    private IFeedbackService iFeedbackService;
//    @Test
//    public void submitFeedback() {
//        try {
//            Integer gameId = 3;
//            String category = "好评";
//            String content = "nice!!!";
//            iFeedbackService.submit(gameId,category,content);
//            System.out.println("提交成功！");
//        } catch (ServiceException e) {
//            System.out.println("添加失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//    }
//    @Test
//    public void handleFeedback() {
//        try {
//            Integer id = 6;
//            String handleComment = "我反对";
//            iFeedbackService.handle(id, handleComment);
//            System.out.println("处理成功！");
//        } catch (ServiceException e) {
//            System.out.println("处理失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//    }
//    @Test
//    public void getFeedback() {
//        try {
//            Integer userId = 0;
//            Integer pageSize = 1;
//            Integer pageNum = 1;
//            List<Feedback> list = iFeedbackService.getFeedback(pageSize,pageNum,userId);
//            for(Feedback feedback : list)
//            {
//                System.out.println(feedback);
//            }
//            System.out.println("处理成功！");
//        } catch (ServiceException e) {
//            System.out.println("处理失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//    }
}
