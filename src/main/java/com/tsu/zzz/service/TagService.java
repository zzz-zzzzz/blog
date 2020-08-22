package com.tsu.zzz.service;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;

import java.util.List;

public interface TagService {
    PageInfo<Tag> findByPage(Integer page, Integer pageSize);

    void save(Tag tag);

    Integer findCount();

    void delete(Long id);

    void update(Tag tag);

    Tag findById(Long id);

    Tag findByName(String name);

    List<Tag> findAll();

    List<Long> findIdByBlogId(Long blogId);

    List<Tag> findAll(Integer size);

}
