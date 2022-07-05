package com.reallyfun.server.controller;

import com.reallyfun.server.entity.User;
import com.reallyfun.server.service.IUserService;
import com.reallyfun.server.service.ex.FileToolException;
import com.reallyfun.server.service.ex.UserException;
import com.reallyfun.server.util.FileTool;
import com.reallyfun.server.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 处理⽤户相关请求的控制器类
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     *
     * @param user 需包含name, password, email
     * @return 响应结果
     */
    @PostMapping("register")
    public ResponseResult<Void> register(@RequestBody User user) {
        // 用户注册
        userService.register(
                user.getName(),
                user.getPassword(),
                user.getEmail()
        );

        // 返回响应结果
        return ResponseResult.getResponseResult("注册成功！");
    }

    /**
     * 用户登录
     *
     * @param user    需包含name, password
     * @param session Http会话对象
     * @return 若登录成功，返回用户信息，否则返回相应错误信息
     */
    @PostMapping("login")
    public ResponseResult<User> login(@RequestBody User user, HttpSession session) {
        // 检查重复登录
        if (getUserFromSession(session) != null) {
            throw new UserException("您已登录");
        }

        // 登录并获取当前用户数据
        User userResult = userService.login(user.getName(), user.getPassword());

        // 将用户数据存入HttpSession中
        setUserIntoSession(userResult, session);

        // 构造并返回响应结果
        User userRet = new User();
        userRet.setId(userResult.getId());
        userRet.setName(userResult.getName());
        userRet.setEmail(userResult.getEmail());
        userRet.setAvatar(userResult.getAvatar());
        userRet.setAuth(userResult.getAuth());
        return ResponseResult.getResponseResult("登录成功！", userRet);
    }

    /**
     * 头像⽂件⼤⼩的上限值（2MB）
     */
    public static final Integer AVATAR_MAX_SIZE = 2 * 1024 * 1024;

    /**
     * 允许上传的头像的⽂件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    /** 初始化允许上传的头像的⽂件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * 头像存储目录
     */
    @Value("${user.avatar.upload-dir}")
    private String avatarUploadDir;

    /**
     * 修改用户头像
     *
     * @param file    头像文件
     * @param session HttpSession对象
     * @return 新的头像文件名结果
     */
    @PostMapping("avatar")
    public ResponseResult<String> uploadAvatar(
            @RequestParam("avatar") MultipartFile file,
            HttpSession session) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new FileToolException("上传的文件不允许为空");
        }

        // 构造文件名
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex <= 0) {
            throw new FileToolException("文件名后缀有误");
        }
        String suffix = originalFilename.substring(beginIndex);
        String filename = UUID.randomUUID().toString() + suffix;

        // 上传头像文件
        FileTool.uploadFile(
                file,
                avatarUploadDir,
                filename,
                AVATAR_MAX_SIZE,
                AVATAR_TYPES
        );

        // 删除旧头像
        User user = getUserFromSession(session);
        String oldFilename = userService.getAvatarById(user.getId());
        FileTool.deleteFile(avatarUploadDir, oldFilename);

        // 更新用户头像文件名
        userService.updateAvatar(user.getId(), filename);

        // 返回新头像文件名
        return ResponseResult.getResponseResult("头像修改成功", filename);
    }
}
