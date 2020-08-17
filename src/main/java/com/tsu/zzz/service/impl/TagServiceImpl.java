package com.tsu.zzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.dao.TagMapper;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag findByName(String name) {
        return tagMapper.findByName(name);
    }

    @Override
    public List<Tag> findAll() {
        return tagMapper.findAll();
    }

    @Override
    public List<Long> findIdByBlogId(Long blogId) {
        return tagMapper.findIdByBlogId(blogId);
    }

    @Override
    public PageInfo<Tag> findByPage(Integer page, Integer pageSize) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 6;
        }
        PageHelper.startPage(page,pageSize);
        List<Tag> tagList = tagMapper.findAll();
        return new PageInfo<Tag>(tagList);
    }

    @Transactional
    @Override
    public void save(Tag tag) {
        tagMapper.save(tag);
    }

    @Override
    public Integer findCount() {
        return tagMapper.findCount();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        tagMapper.delete(id);
    }

    @Transactional
    @Override
    public void update(Tag tag) {
        tagMapper.update(tag);
    }

    @Override
    public Tag findById(Long id) {
        return tagMapper.findById(id);
    }
}
