package com.reallyfun.server.service;

import com.reallyfun.server.entity.Tag;

public interface ITagService {
    /**
     * 新建标签
     *
     * @param content 标签内容
     * @param userId  创建人ID
     */
    void insert(String content, Integer userId);

    /**
     * 根据标签ID删除标签
     *
     * @param id 标签ID
     */
    void delete(Integer id);

    /**
     * 绑定游戏标签
     *
     * @param gameId 目标游戏ID
     * @param tagId  标签ID
     * @param userId 操作人ID
     */
    void bind(Integer gameId, Integer tagId, Integer userId);

    /**
     * 解绑游戏ID
     *
     * @param gameId 目标游戏ID
     * @param tagId  标签ID
     */
    void unbind(Integer gameId, Integer tagId);
}
