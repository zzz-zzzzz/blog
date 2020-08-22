package com.tsu.zzz.dao;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BlogMapper {
    Blog findById(Long id);

    List<Blog> findAll(Blog blog);

    void save(Blog blog);

    void update(Blog blog);

    void delete(Long id);

    Blog findByTitle(String title);

    Integer findCount();

    List<Blog> findByTypeId(Long typeId);

    @Select("select id,title from t_blog  where published=1 and recommend=1 order by create_time desc limit 0,#{size}")
    List<Blog> findByCreateTimeDesc(Integer size);

    List<Blog> findByTagId(Long tagId);

    List<Blog> findByTitleOrDescription(Blog blog);

    @Update("update t_blog set views=views+1 where id=#{id}")
    void updateViews(Long id);

    @Select("SELECT DATE_FORMAT(b.create_time,'%Y') AS YEAR FROM t_blog b GROUP BY YEAR ORDER BY YEAR DESC")
    List<String> findGroupBlogYears();

    List<Blog> findBlogByYear(String year);
}
