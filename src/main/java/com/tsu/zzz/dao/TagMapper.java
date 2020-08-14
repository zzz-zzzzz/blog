package com.tsu.zzz.dao;

import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {
    @Insert("insert into t_tag(name) values(#{name})")
    void save(Tag tag);

    @Select("select * from t_tag")
    List<Tag> findAll();

    @Update("update t_tag set name=#{name} where id=#{id}")
    void update(Tag tag);

    @Delete("delete from t_tag where id=#{id}")
    void delete(Long id);

    @Select("select * from t_tag where id=#{id}")
    Tag findById(Long id);

    @Select("select count(*) from t_tag ")
    Integer findCount();
}
