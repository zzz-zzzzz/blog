package com.tsu.zzz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.dao.TypeMapper;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Integer findCount() {
        return typeMapper.findCount();
    }

    @Transactional
    @Override
    public void save(Type type) {
        typeMapper.save(type);
    }

    @Override
    public List<Type> findAll() {
        return typeMapper.findAll();
    }

    @Override
    public Type findByName(String name) {
        return typeMapper.findByName(name);
    }


    @Override
    public List<Type> findAll(Integer size) {
        return typeMapper.findAllBySize(size);
    }

    @Override
    public PageInfo<Type> findByPage(Integer page, Integer pageSize) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 6;
        }
        PageHelper.startPage(page, pageSize);
        List<Type> typeList = typeMapper.findAll();
        return new PageInfo<Type>(typeList);
    }

    @Transactional
    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }


    @Override
    public Type findById(Long id) {
        return typeMapper.findById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        typeMapper.delete(id);
    }
}
