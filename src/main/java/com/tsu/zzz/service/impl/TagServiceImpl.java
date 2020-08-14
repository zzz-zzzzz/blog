package com.tsu.zzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.dao.TagMapper;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

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

    @Override
    public void save(Tag tag) {
        tagMapper.save(tag);
    }

    @Override
    public Integer findCount() {
        return tagMapper.findCount();
    }

    @Override
    public void delete(Long id) {
        tagMapper.delete(id);
    }

    @Override
    public void update(Tag tag) {
        tagMapper.update(tag);
    }

    @Override
    public Tag findById(Long id) {
        return tagMapper.findById(id);
    }
}
