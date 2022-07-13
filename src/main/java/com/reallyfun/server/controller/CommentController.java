package com.reallyfun.server.controller;

import com.reallyfun.server.entity.Comment;
import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.ICommentService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理评论相关请求的控制器类
 */
@RestController
public class CommentController extends BaseController {
    @Autowired
    ICommentService commentService;

    /**
     * 发表评论
     *
     * @param gameId  游戏ID
     * @param content 评论内容
     * @param replyId 要回复的评论ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @PostMapping("/comment")
    public ResponseResult<Void> insert(
            @RequestParam("game_id") Integer gameId,
            @RequestParam("content") String content,
            @RequestParam(value = "reply_id", required = false) Integer replyId,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 发表评论
        commentService.insert(gameId, content, replyId, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("评论发表成功！");
    }

    /**
     * 删除评论
     *
     * @param id      评论ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @DeleteMapping("/comment/{id}")
    public ResponseResult<Void> delete(
            @PathVariable("id") Integer id,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 删除评论
        commentService.delete(id, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("评论删除成功！");
    }

    /**
     * 为评论点赞
     *
     * @param id      评论ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @PostMapping("/comment/like")
    public ResponseResult<Void> like(
            @RequestParam Integer id,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 点赞
        commentService.like(id, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("点赞成功！");
    }

    /**
     * 取消点赞
     *
     * @param id      评论ID
     * @param session HttpSession对象
     * @return 响应结果
     */
    @DeleteMapping("/comment/like")
    public ResponseResult<Void> unlike(
            @RequestParam Integer id,
            HttpSession session
    ) {
        // 获取用户对象
        User user = getUserFromSession(session);

        // 取消点赞
        commentService.unlike(id, user.getId());

        // 返回响应结果
        return ResponseResult.getResponseResult("取消点赞成功！");
    }

    /**
     * 获取评论列表
     *
     * @param key      排序关键字，可选"like", "moment"
     * @param order    排序方式，可选升序false, 降序true
     * @param userId   评论发表人ID
     * @param gameId   游戏ID
     * @param replyId  回复的评论ID
     * @param myUserId 我的用户ID，用于获取点赞信息
     * @param isRoot   是否为根节点评论
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 符合条件的评论列表
     */
    @GetMapping("/comments")
    public ResponseResult<List<Comment>> comments(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "order", required = false) Boolean order,
            @RequestParam(value = "user_id", required = false) Integer userId,
            @RequestParam(value = "game_id", required = false) Integer gameId,
            @RequestParam(value = "reply_id", required = false) Integer replyId,
            @RequestParam(value = "my_user_id", required = false) Integer myUserId,
            @RequestParam(value = "is_root", required = false) Boolean isRoot,
            @RequestParam("page_size") Integer pageSize,
            @RequestParam("page_num") Integer pageNum
    ) {
        // 获取评论列表并返回
        List<Comment> result = commentService.findAllOfPage(
                key, order, userId, gameId, replyId,
                myUserId, isRoot, pageSize, pageNum
        );
        return ResponseResult.getResponseResult(result);
    }

    /**
     * 查询符合条件的评论数量
     *
     * @param userId   评论发表人ID
     * @param gameId   游戏ID
     * @param replyId  回复的评论ID
     * @param myUserId 我的用户ID，用于获取点赞信息
     * @param isRoot   是否为根节点评论
     * @return 评论数量
     */
    @GetMapping("/comment/count")
    public ResponseResult<Integer> count(
            @RequestParam(value = "user_id", required = false) Integer userId,
            @RequestParam(value = "game_id", required = false) Integer gameId,
            @RequestParam(value = "reply_id", required = false) Integer replyId,
            @RequestParam(value = "my_user_id", required = false) Integer myUserId,
            @RequestParam(value = "is_root", required = false) Boolean isRoot
    ) {
        // 查询数量并返回
        Integer result = commentService.count(
                userId, gameId, replyId, myUserId, isRoot
        );
        return ResponseResult.getResponseResult(result);
    }
}
