package com.tsu.zzz.service;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;

import java.util.List;

public interface BlogService {

    Blog findById(Long id);

    PageInfo<Blog> findByPage(Integer page, Integer size, Blog blog);

    void save(Blog blog, List<Long> tagIds);

    void update(Blog blog,List<Long> tagIds);

    void delete(Long id);

    Blog findByTitle(String title);

    Integer findCount();
}
