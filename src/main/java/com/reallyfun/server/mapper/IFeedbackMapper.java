package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Feedback;

import java.util.List;

public interface IFeedbackMapper {
    /**
     * 新增反馈
     *
     * @param feedback 反馈实体对象
     * @return 受影响的行数
     */
    Integer insert(Feedback feedback);

    /**
     * 根据反馈ID删除反馈
     *
     * @param id 反馈ID
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 根据反馈ID更新处理信息
     *
     * @param feedback 反馈实体对象，需修改id, handlerId, handleComment及修改日志项
     * @return 受影响的行数
     */
    Integer updateHandleInfoById(Feedback feedback);

    /**
     * 获取范围内符合条件的反馈数据
     *
     * @param userId    用户ID（可空）
     * @param gameId    游戏ID（可空）
     * @param category  分类（可空）
     * @param isHandled 是否已被处理（可空）
     * @param offset    偏移量
     * @param limit     记录数
     * @return 反馈列表
     */
    List<Feedback> findAllFeedbackOfRange(
            Integer userId,
            Integer gameId,
            Integer category,
            Boolean isHandled,
            Integer offset,
            Integer limit
    );

    /**
     * 根据反馈ID判断反馈是否存在
     *
     * @param id 反馈ID
     * @return 若存在则返回1，否则返回null
     */
    Integer existById(Integer id);

    /**
     * 根据反馈ID获取反馈对象
     *
     * @param id 反馈ID
     * @return 若存在则返回反馈对象，否则返回null
     */
    Feedback findById(Integer id);
}
