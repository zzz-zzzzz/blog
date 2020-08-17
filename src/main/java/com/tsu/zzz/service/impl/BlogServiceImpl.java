package com.tsu.zzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.dao.BlogMapper;
import com.tsu.zzz.dao.TagMapper;
import com.tsu.zzz.dao.TypeMapper;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.service.BlogService;
import com.tsu.zzz.service.TypeService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Blog findById(Long id) {
        Blog blog = blogMapper.findById(id);
        blog.initTagIds(blog.getTagList());
        return blog;
    }

    @Override
    public PageInfo<Blog> findByPage(Integer page, Integer size, Blog blog) {
        if (blog == null) {
            blog = new Blog();
        }
        if (page == null || page == 0) {
            page = 1;
        }
        if (size == null) {
            size = 6;
        }
        PageHelper.startPage(page, size);
        List<Blog> blogList = blogMapper.findAll(blog);
        return new PageInfo<Blog>(blogList);
    }

    @Override
    public Blog findByTitle(String title) {
        return blogMapper.findByTitle(title);
    }

    @Override
    public Integer findCount() {
        return blogMapper.findCount();
    }

    @Transactional
    @Override
    public void save(Blog blog, List<Long> tagIds) {
        Date date = new Date();
        blog.setCreateTime(date);
        this.saveAndUpdateCommonCode(blog, date);
        blog.setViews(0);
        blogMapper.save(blog);
        Long blogId = blog.getId();
        for (Long tagId : tagIds) {
            if (tagId == null) {
                continue;
            }
            tagMapper.insertTBlogTag(blogId, tagId);
        }

    }

    @Transactional
    @Override
    public void update(Blog blog, List<Long> tagIds) {
        Long blogId = blog.getId();
        tagMapper.deleteTBlogTagByBlogId(blogId);
        this.saveAndUpdateCommonCode(blog, new Date());
        blogMapper.update(blog);
        for (Long tagId : tagIds) {
            if (tagId == null) {
                continue;
            }
            tagMapper.insertTBlogTag(blogId, tagId);
        }

    }

    public void saveAndUpdateCommonCode(Blog blog, Date date) {

        blog.setUpdateTime(date);
        if (blog.getCommentable() == null) {
            blog.setCommentable(false);
        }
        if (blog.getAppreciation() == null) {
            blog.setAppreciation(false);
        }
        if (blog.getPublished() == null) {
            blog.setPublished(false);
        }
        if (blog.getShareStatement() == null) {
            blog.setShareStatement(false);
        }
        if (blog.getRecommend() == null) {
            blog.setRecommend(false);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        tagMapper.deleteTBlogTagByBlogId(id);
        blogMapper.delete(id);
    }
}
