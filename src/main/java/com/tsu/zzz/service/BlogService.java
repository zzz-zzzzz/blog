package com.tsu.zzz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.pojo.Tag;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog findById(Long id);

    PageInfo<Blog> findByPage(Integer page, Integer size, Blog blog);

    void save(Blog blog, List<Long> tagIds);

    void update(Blog blog, List<Long> tagIds);

    void delete(Long id);

    Blog findByTitle(String title);

    Integer findCount();

    List<Blog> findByCreateTimeDesc(Integer size);

    PageInfo<Blog> findByTitleOrDescription(Integer page, Integer size, Blog blog);

    Blog findByIdAndConvertHtml(Long id);

    void updateViews(Long id);

    PageInfo<Blog> findByTypeId(Long id, Integer page, Integer size);

    PageInfo<Blog> findByTagId(Long id, Integer page, Integer size);

    Map<String, List<Blog>> findBlogByYear();

}
