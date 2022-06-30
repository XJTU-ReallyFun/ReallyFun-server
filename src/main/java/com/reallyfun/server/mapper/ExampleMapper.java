package com.reallyfun.server.mapper;

import com.reallyfun.server.entity.Example;
import org.apache.ibatis.annotations.Param;

/**
 * 处理数据操作的持久层接⼝
 */
public interface ExampleMapper {
    /**
     * 插入数据
     *
     * @param example 数据
     * @return 受影响行数
     */
    Integer insert(Example example);

    /**
     * 根据eid删除数据
     *
     * @param eid 数据id
     * @return 受影响的行数
     */
    Integer deleteByEid(Integer eid);

    /**
     * 根据eid更新estr
     *
     * @param example 定位依据及目标修改值
     * @return 受影响的行数
     */
    Integer updateEstrByEid(Example example, String dstEstr);

    /**
     * 根据eid查询数据
     *
     * @param eid 数据id
     * @return 匹配到的数据，若无则返回null
     */
    Example findByEid(Integer eid);
}
