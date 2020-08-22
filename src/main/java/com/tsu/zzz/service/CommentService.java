package com.tsu.zzz.service;

import com.tsu.zzz.pojo.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment);

    List<Comment> findByBlogId(Long blogId);
}
