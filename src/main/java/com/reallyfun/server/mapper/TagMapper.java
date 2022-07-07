package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;

public interface TagMapper {
    /**
     * 插入新的标签
     * @param content 标签tag
     * @return 受影响的行数
     */
    Integer insertTag(String content);

    /**
     * 通过id删除标签
     * @param id 标签
     * @return 受影响的行数
     */
    Integer deleteTag(Integer id);

    /**
     * 绑定关系
     * @param relgametag
     * @return 受影响的行数
     */
    Integer bindGameTag(RelGameTag relgametag);

    /**
     * 解除绑定关系
     * @param gameId,tagId
     * @return 受影响的行数
     */
    Integer unbindGameTag(Integer gameId, Integer tagId);
}
