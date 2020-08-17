package com.tsu.zzz.dao;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

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
}
