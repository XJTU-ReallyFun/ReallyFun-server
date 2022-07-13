package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;

public interface ITagMapper {
    /**
     * 新建标签
     *
     * @param tag 标签实体对象
     * @return 受影响的行数
     */
    Integer insert(Tag tag);

    /**
     * 根据标签ID删除标签
     *
     * @param id 标签ID
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 判断标签是否存在
     *
     * @param id 标签ID
     * @return 若存在则返回1，否则返回null
     */
    Integer existById(Integer id);

    /**
     * 判断是否存在内容为content的标签
     *
     * @param content 标签内容
     * @return 若存在则返回1，否则返回null
     */
    Integer existByContent(String content);

    /**
     * 插入游戏-标签关系
     *
     * @param rel 游戏-标签关系实体对象
     * @return 受影响的行数
     */
    Integer insertRel(RelGameTag rel);

    /**
     * 删除游戏-标签关系
     *
     * @param gameId 游戏ID
     * @param tagId  标签ID
     * @return 受影响的行数
     */
    Integer deleteRelByIds(Integer gameId, Integer tagId);

    /**
     * 判断游戏-标签关系是否存在
     *
     * @param gameId 游戏ID
     * @param tagId  标签ID
     * @return 若存在，则返回1；否则返回null
     */
    Integer existRelByIds(Integer gameId, Integer tagId);
}
