package com.tsu.zzz.dao;

import com.tsu.zzz.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Insert("insert into t_type(name) values(#{name})")
    void save(Type type);

    @Select("select * from t_type")
    @Results(id="typeResultMap01",value={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(property = "blogList",column = "id",many = @Many(select = "com.tsu.zzz.dao.BlogMapper.findByTypeId"))
    })
    List<Type> findAll();

    @Update("update t_type set name=#{name} where id=#{id}")
    void update(Type type);

    @Delete("delete from t_type where id=#{id}")
    void delete(Long id);

    @Select("select * from t_type where id=#{id}")
    Type findById(Long id);

    @Select("select count(*) from t_type ")
    Integer findCount();

    @Select("select * from t_type where name=#{name}")
    Type findByName(String name);

    @ResultMap("typeResultMap01")
    @Select("select * from t_type limit 0,#{size}")
    List<Type> findAllBySize(Integer size);
}
