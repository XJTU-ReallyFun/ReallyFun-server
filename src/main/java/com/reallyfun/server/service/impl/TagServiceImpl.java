package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.RelGameTag;
import com.reallyfun.server.entity.Tag;
import com.reallyfun.server.mapper.TagMapper;
import com.reallyfun.server.service.ITagService;
import com.reallyfun.server.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public void insertTag(String content){
        // 表示⽤户名没有被占⽤，则允许注册
        // 调⽤持久层Integer insert(User user)⽅法，执⾏注册并获取返回值(受影响的⾏数)
        Integer rows = tagMapper.insertTag(content);
        // 判断受影响的⾏数是否不为1
        if (rows != 1) {
            // 是：插⼊数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加标签数据出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void deleteTag(Integer id){
        // 调⽤持久层Integer insert(User user)⽅法，执⾏注册并获取返回值(受影响的⾏数)
        Integer rows = tagMapper.deleteTag(id);
        // 判断受影响的⾏数是否不为1
        if (rows != 1) {
            // 是：插⼊数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("删除标签数据出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void bindGameTag(RelGameTag relgametag){
        // 创建当前时间对象
        Date now = new Date();
        // 补全数据：4项⽇志属性
        relgametag.setCreatedUser(1);
        relgametag.setCreatedTime(now);
        relgametag.setModifiedUser(1);
        relgametag.setModifiedTime(now);

        Integer rows = tagMapper.bindGameTag(relgametag);
        if (rows != 1) {
            // 是：插⼊数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加绑定关系出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void unbindGameTag(Integer gameId, Integer tagId){
        Integer rows = tagMapper.unbindGameTag(gameId, tagId);
        // 判断受影响的⾏数是否不为1
        if (rows != 1) {
            // 是：插⼊数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("删除绑定关系出现未知错误，请联系系统管理员");
        }
    }
}
