package com.tsu.zzz.service;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;

public interface TagService {
    PageInfo<Tag> findByPage(Integer page, Integer pageSize);

    void save(Tag tag);

    Integer findCount();

    void delete(Long id);

    void update(Tag tag);

    Tag findById(Long id);
}
