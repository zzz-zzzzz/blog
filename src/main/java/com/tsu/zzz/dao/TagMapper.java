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

    @Select("select * from t_tag where name=#{name}")
    Tag findByName(String name);

    @Insert("insert into t_blog_tag(blog_id,tag_id) values(#{blogId},#{tagId})")
    void insertTBlogTag(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    @Select("select tag_id from t_blog_tag where blog_id=#{blogId}")
    List<Long> findIdByBlogId(Long blogId);

    @Select("select  t_tag.* from t_tag inner join t_blog_tag on t_tag.id=t_blog_tag.tag_id where blog_id=#{blogId}")
    List<Tag> findByBlogId(Long blogId);

    @Delete("delete from t_blog_tag where blog_id=#{blogId}")
    void deleteTBlogTagByBlogId(Long blogId);
}
