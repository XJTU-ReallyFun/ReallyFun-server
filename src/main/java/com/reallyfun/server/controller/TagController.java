package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Tag;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.ITagService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 处理标签相关请求的控制器类
 */
@RestController
public class TagController extends BaseController {
    @Autowired
    private ITagService tagService;

    /**
     * 新建标签
     *
     * @param tag 标签实体对象
     * @return 响应结果
     */
    @PostMapping("/tag")
    public ResponseResult<Void> insert(
            @RequestBody Tag tag,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 创建标签
        tagService.insert(tag.getContent(), user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("标签创建成功！");
    }

    /**
     * 删除标签
     *
     * @param id 标签ID
     * @return 响应结果
     */
    @DeleteMapping("/tag/{id}")
    public ResponseResult<Void> delete(@PathVariable Integer id) {
        // 删除标签
        tagService.delete(id);

        // 返回响应结果
        return ResponseResult.getResponseResult("标签删除成功！");
    }

    /**
     * 绑定游戏标签
     *
     * @param gameId 目标游戏ID
     * @param tagId  标签ID
     * @return 响应结果
     */
    @PostMapping("/tag/bind")
    public ResponseResult<Void> bind(
            @RequestParam("game_id") Integer gameId,
            @RequestParam("tag_id") Integer tagId,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 绑定游戏标签
        tagService.bind(gameId, tagId, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("标签绑定成功！");
    }

    /**
     * 解绑游戏标签
     *
     * @param gameId 目标游戏ID
     * @param tagId  标签ID
     * @return 响应结果
     */
    @DeleteMapping("/tag/unbind")
    public ResponseResult<Void> unbind(
            @RequestParam("game_id") Integer gameId,
            @RequestParam("tag_id") Integer tagId
    ) {
        // 解绑游戏标签
        tagService.unbind(gameId, tagId);

        // 返回响应结果
        return ResponseResult.getResponseResult("标签解绑成功！");
    }
}