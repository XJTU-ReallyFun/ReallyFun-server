package com.reallyfun.server.mapper;


import com.reallyfun.server.entity.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExampleMapperTests {
    @Autowired(required = false)
    private IExampleMapper exampleMapper;

    @Test
    public void insert() {
        Example example = new Example();
        example.setEint(2333);
        example.setEstr("haha");
        example.createBy(-1);
        Integer rows = exampleMapper.insert(example);
        System.out.println("rows=" + rows);

        Example example2 = new Example();
        example2.setEint(666);
        example2.setEstr("hehe");
        example2.createBy(-1);
        rows = exampleMapper.insert(example2);
        System.out.println("rows=" + rows);
    }

    @Test
    public void deleteByEid() {
        Integer rows = exampleMapper.deleteByEid(1);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateEstrByEid() {
        Example example = new Example();
        example.setEid(2);
        example.modifiedBy(-1);
        Integer rows = exampleMapper.updateEstrByEid(example, "user233");
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByEid() {
        Example result = exampleMapper.findByEid(0);
        System.out.println(result);
    }
}