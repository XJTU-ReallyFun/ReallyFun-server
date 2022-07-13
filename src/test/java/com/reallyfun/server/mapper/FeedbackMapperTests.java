package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Feedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class FeedbackMapperTests {
    @Autowired(required = false)
    private IFeedbackMapper feedbackMapper;

    private List<Feedback> feedbackList;

    public static Integer randint(Integer min, Integer max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private Boolean generate() {
        if (feedbackList == null) {
            System.out.println("GENERATE");
            feedbackList = new ArrayList<Feedback>();
            for (int i = 0; i < 100; i++) {
                Feedback feedback = new Feedback();
                feedback.setUserId(randint(1, 20));
                feedback.setGameId(randint(1, 20));
                feedback.setCategory(randint(1, 20));
                feedback.setContent(UUID.randomUUID().toString());
                if (randint(0, 1) == 1) {
                    feedback.setHandlerId(randint(1, 20));
                    feedback.setHandleComment(UUID.randomUUID().toString());
                }
                feedback.createBy(feedback.getUserId());
                feedbackList.add(feedback);
            }
            return true;
        }
        return false;
    }

    @Test
    public void insert() {
        generate();
        System.out.println("INSERT");
        for (Feedback feedback : feedbackList) {
            Integer result = feedbackMapper.insert(feedback);
            assert result == 1;
        }
    }

    @Test
    public void deleteById() {
        if (generate()) {
            insert();
        }
        System.out.println("DELETE");
        for (Feedback feedback : feedbackList) {
            Integer result = feedbackMapper.deleteById(feedback.getId());
            assert result == 1;
        }
        feedbackList = null;
    }

    @Test
    public void updateHandleInfoById() {
        if (generate()) {
            insert();
        }
        System.out.println("UPDATE");
        for (Feedback feedback : feedbackList) {
            if (randint(0, 1) == 1) {
                Integer handlerId = randint(1, 20);
                String handleComment = UUID.randomUUID().toString();
                System.out.println(
                        "updateHandleInfoById: " + feedback.toString() +
                                " handlerId = " + handlerId +
                                " handleComment = " + handleComment
                );
                Feedback fb = new Feedback();
                fb.setId(feedback.getId());
                fb.setHandlerId(handlerId);
                fb.setHandleComment(handleComment);
                fb.modifiedBy(feedback.getUserId());
                Integer result = feedbackMapper.updateHandleInfoById(fb);
                System.out.println("result = " + result);
                assert result == 1;
            }
        }
    }

    @Test
    public void findAllFeedbackOfRange() {
        if (generate()) {
            insert();
        }
        Integer userId = feedbackList.get(0).getUserId();
        Integer gameId = feedbackList.get(0).getGameId();
        Integer category = feedbackList.get(0).getCategory();

        List<Feedback> fbs = feedbackMapper.findAllFeedbackOfRange(null, null, null, null, 10, 15);
        System.out.println("FIND (NO CONDITION)");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }

        fbs = feedbackMapper.findAllFeedbackOfRange(userId, null, null, null, 1, 3);
        System.out.println("FIND (USERID = " + userId + ")");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }

        fbs = feedbackMapper.findAllFeedbackOfRange(null, gameId, null, null, 1, 3);
        System.out.println("FIND (GAMEID = " + gameId + ")");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }

        fbs = feedbackMapper.findAllFeedbackOfRange(userId, gameId, null, null, 1, 3);
        System.out.println("FIND (USERID = " + userId + ", GAMEID = " + gameId + ")");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }

        fbs = feedbackMapper.findAllFeedbackOfRange(null, null, category, null, 1, 3);
        System.out.println("FIND (CATEGORY = " + category + ")");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }

        fbs = feedbackMapper.findAllFeedbackOfRange(null, null, null, true, 1, 3);
        System.out.println("FIND (ISHANDLED = TRUE)");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }

        fbs = feedbackMapper.findAllFeedbackOfRange(null, null, null, false, 1, 3);
        System.out.println("FIND (ISHANDLED = FALSE)");
        for (Feedback fb : fbs) {
            System.out.println(fb);
        }
    }

    @Test
    public void existById() {
        generate();
        Integer result = feedbackMapper.existById(10);
        assert result != null;
        result = feedbackMapper.existById(-1);
        assert result == null;
    }

    @Test
    public void findById() {
        generate();
        Feedback result = feedbackMapper.findById(10);
        assert result != null;
        System.out.println("ID = 10, RESULT = " + result);

        result = feedbackMapper.findById(-1);
        assert result == null;
        System.out.println("ID = -1, RESULT = " + result);
    }
}
