package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;
import com.reallyfun.server.mapper.IGameMapper;
import com.reallyfun.server.mapper.ITagMapper;
import com.reallyfun.server.service.ITagService;
import com.reallyfun.server.service.ex.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理标签数据的业务层实现类
 */
@Service
public class TagServiceImpl implements ITagService {
    @Autowired(required = false)
    private ITagMapper tagMapper;

    @Autowired(required = false)
    private IGameMapper gameMapper;

    private static final Integer MAX_CONTENT_LENGTH = 20;

    @Override
    public void insert(String content, Integer userId) {
        // 限制标签内容字数
        if (content.length() < 1 || MAX_CONTENT_LENGTH < content.length()) {
            throw new TagException("标签内容字数有误");
        }

        // 不允许相同内容的标签存在
        if (tagMapper.existByContent(content) != null) {
            throw new TagException("标签内容已存在");
        }

        // 构造标签数据
        Tag tagData = new Tag();
        tagData.setContent(content);
        tagData.createBy(userId);

        // 插入并判断是否成功
        Integer result = tagMapper.insert(tagData);
        if (result != 1) {
            throw new TagException("标签创建失败");
        }
    }

    @Override
    public void delete(Integer id) {
        // 删除并判断是否成功
        Integer result = tagMapper.deleteById(id);
        if (result != 1) {
            throw new TagException("标签删除失败");
        }
    }

    @Override
    public void bind(Integer gameId, Integer tagId, Integer userId) {
        // 判断游戏是否存在
        if (gameMapper.findById(gameId) == null) {
            throw new TagException("游戏不存在");
        }

        // 判断标签是否存在
        if (tagMapper.existById(tagId) == null) {
            throw new TagException("标签不存在");
        }

        // 判断是否已经绑定过
        if (tagMapper.existRelByIds(gameId, tagId) != null) {
            throw new TagException("标签已经绑定");
        }

        // 构造游戏-标签关系数据
        RelGameTag rel = new RelGameTag();
        rel.setGameId(gameId);
        rel.setTagId(tagId);
        rel.createBy(userId);

        // 插入并判断是否成功
        Integer result = tagMapper.insertRel(rel);
        if (result != 1) {
            throw new TagException("绑定失败");
        }
    }

    @Override
    public void unbind(Integer gameId, Integer tagId) {
        // 判断绑定关系是否存在
        if (tagMapper.existRelByIds(gameId, tagId) == null) {
            throw new TagException("绑定关系不存在");
        }

        // 解绑并判断是否成功
        Integer result = tagMapper.deleteRelByIds(gameId, tagId);
        if (result != 1) {
            throw new TagException("解绑失败");
        }
    }
}
