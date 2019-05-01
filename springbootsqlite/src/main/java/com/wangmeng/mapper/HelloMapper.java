package com.wangmeng.mapper;


import com.wangmeng.model.HelloModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HelloMapper {

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO hello(title, text) VALUES(#{title}, #{text})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_master WHERE (name = 'hello')", before = false, keyProperty = "id", resultType = int.class)
    int insert(HelloModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM hello WHERE id=#{id}")
    HelloModel select(int id);

    // 查询全部

    /**
     * 如果没有做数据表与实体类的映射，
     * 可以把它们的字段写成一样
     * @return
     */
    @Select("SELECT * FROM hello")
    List<HelloModel> selectAll();

    // 更新 value
    @Update("UPDATE hello SET title=#{title} WHERE id=#{id}")
    int updateValue(HelloModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM hello WHERE id=#{id}")
    int delete(Integer id);

}