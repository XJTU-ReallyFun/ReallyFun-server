package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Feedback;

import java.util.List;

public interface FeedbackMapper {
    /**
     * @param gameId
     * @param category
     * @param content
     * @return 受影响的行数
     */
    Integer submitFeedback(Integer gameId, String category, String content);
    /**
     * @param id
     * @return 受影响的函数
     */
    Integer deleteFeedback(Integer id);
    /**
     * @param id
     * return 匹配到的用户数据
     */
    Feedback findById(Integer id);
    /**
     * @param id
     * @param handleComment
     * @return 受影响的行数
     */
    Integer handleFeedback(Integer id, String handleComment);
    /**
     * @param userId
     * @return 匹配到的用户数据
     */
    List<Feedback> findByUid(Integer userId);
}
