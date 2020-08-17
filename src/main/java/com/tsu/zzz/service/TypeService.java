package com.tsu.zzz.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Type;

import java.util.List;

public interface TypeService {

    void save(Type type);

    List<Type> findAll();

    void update(Type type);

    void delete(Long id);

    Type findById(Long id);

    PageInfo<Type> findByPage(Integer page, Integer pageSize);

    Integer findCount();

    Type findByName(String name);
}
