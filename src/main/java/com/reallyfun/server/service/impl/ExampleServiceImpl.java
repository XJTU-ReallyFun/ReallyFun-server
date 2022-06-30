package com.reallyfun.server.service.impl;

import com.reallyfun.server.entity.Example;
import com.reallyfun.server.mapper.ExampleMapper;
import com.reallyfun.server.service.IExampleService;
import com.reallyfun.server.service.ex.ExampleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理数据的业务层实现类
 */
@Service
public class ExampleServiceImpl implements IExampleService {
    @Autowired(required = false)
    private ExampleMapper exampleMapper;

    @Override
    public void add(int eint, String estr) {
        Example example = new Example();
        example.setEint(eint);
        example.setEstr(estr);
        example.createBy("username");
        Integer result = exampleMapper.insert(example);
        if (result != 1) {
            throw new ExampleException("示例插入失败");
        }
    }
}
