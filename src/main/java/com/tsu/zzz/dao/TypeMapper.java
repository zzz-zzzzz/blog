package com.tsu.zzz.dao;

import com.tsu.zzz.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Insert("insert into t_type(name) values(#{name})")
    void save(Type type);

    @Select("select * from t_type")
    List<Type> findAll();

    @Update("update t_type set name=#{name} where id=#{id}")
    void update(Type type);

    @Delete("delete from t_type where id=#{id}")
    void delete(Long id);

    @Select("select * from t_type where id=#{id}")
    Type findById(Long id);
}
