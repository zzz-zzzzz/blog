package com.tsu.zzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsu.zzz.dao.BlogMapper;
import com.tsu.zzz.dao.TagMapper;
import com.tsu.zzz.dao.TypeMapper;
import com.tsu.zzz.exception.NotFoundException;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.service.BlogService;
import com.tsu.zzz.service.TypeService;
import com.tsu.zzz.utils.MarkdownUtil;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        blog = checkQuery(blog);
        page = checkPage(page);
        size = checkSize(size);
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

    @Override
    public List<Blog> findByCreateTimeDesc(Integer size) {
        return blogMapper.findByCreateTimeDesc(size);
    }

    @Override
    public PageInfo<Blog> findByTitleOrDescription(Integer page, Integer size, Blog blog) {
        blog = checkQuery(blog);
        page = checkPage(page);
        size = checkSize(size);
        PageHelper.startPage(page, size);
        List<Blog> blogList = blogMapper.findByTitleOrDescription(blog);
        return new PageInfo<Blog>(blogList);
    }

    @Override
    public Blog findByIdAndConvertHtml(Long id) {
        Blog blog = blogMapper.findById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String htmlContent = MarkdownUtil.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(htmlContent);
        return blog;
    }

    @Override
    @Transactional
    public void updateViews(Long id) {
        blogMapper.updateViews(id);
    }

    @Transactional
    @Override
    public void save(Blog blog, List<Long> tagIds) {
        Date date = new Date();
        blog.setCreateTime(date);
        this.checkBlog(blog, date);
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

    public Blog checkQuery(Blog blog) {
        if (blog == null) {
            return new Blog();
        }
        return blog;
    }

    public Integer checkPage(Integer page) {
        if (page == null || page <= 0) {
            return 1;
        }
        return page;
    }

    public Integer checkSize(Integer size) {
        if (size == null) {
            return 6;
        }
        return size;
    }

    @Override
    public PageInfo<Blog> findByTypeId(Long id, Integer page, Integer size) {
        page = checkPage(page);
        size = checkSize(size);
        PageHelper.startPage(page, size);
        List<Blog> blogList = blogMapper.findByTypeId(id);
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> findByTagId(Long id, Integer page, Integer size) {
        page = checkPage(page);
        size = checkSize(size);
        PageHelper.startPage(page, size);
        List<Blog> blogList = blogMapper.findByTagId(id);
        return new PageInfo<>(blogList);
    }

    @Override
    public Map<String, List<Blog>> findBlogByYear() {
        List<String> years = blogMapper.findGroupBlogYears();
        Map<String, List<Blog>> blogByYear = new HashMap<>();
        for (String year : years) {
            List<Blog> blogList = blogMapper.findBlogByYear(year);
            blogByYear.put(year, blogList);
        }
        return blogByYear;
    }

    @Override
    @Transactional
    public void update(Blog blog, List<Long> tagIds) {
        Long blogId = blog.getId();
        tagMapper.deleteTBlogTagByBlogId(blogId);
        this.checkBlog(blog, new Date());
        blogMapper.update(blog);
        for (Long tagId : tagIds) {
            if (tagId == null) {
                continue;
            }
            tagMapper.insertTBlogTag(blogId, tagId);
        }

    }

    public void checkBlog(Blog blog, Date date) {
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
