package com.reallyfun.server.controller;

import com.reallyfun.server.service.ITagService;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 处理⽤户相关请求的控制器类 */
@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    private ITagService tagService;
    @PostMapping("insertTag")
    public ResponseResult<Void> insertTag(String content) {
        // 调⽤业务对象执⾏注册
        tagService.insertTag(content);
        // 响应成功
        return ResponseResult.getResponseResult("添加成功");
    }
    @DeleteMapping ("{id}")
    public ResponseResult<Void> deleteTag(@PathVariable Integer id) {
        // 调⽤业务对象执⾏注册
        tagService.deleteTag(id);
        // 响应成功
        return ResponseResult.getResponseResult("删除成功");
    }

    @PostMapping("bind")
    public ResponseResult<Void> bindGameTag(@RequestParam("game_id") Integer gameId,@RequestParam("tag_id") Integer tagId) {
        // 调⽤业务对象执⾏注册
        tagService.bindGameTag(gameId,tagId);
        // 响应成功
        return ResponseResult.getResponseResult("绑定成功");
    }

    @DeleteMapping("unbind")
    public ResponseResult<Void> unbindGameTag(@RequestParam("game_id") Integer gameId,@RequestParam("tag_id") Integer tagId) {
        // 调⽤业务对象执⾏注册
        tagService.unbindGameTag(gameId, tagId);
        // 响应成功
        return ResponseResult.getResponseResult("解除绑定成功");
    }
}