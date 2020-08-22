package com.tsu.zzz.controller;

import com.tsu.zzz.pojo.Comment;
import com.tsu.zzz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/get/{blogId}")
    public String findByBlogId(@PathVariable Long blogId, Model model) {
        List<Comment> commentList = commentService.findByBlogId(blogId);
        model.addAttribute("commentList", commentList);
        return "blog::commentList";
    }

    @PostMapping("/comment/save")
    public String save(Comment comment) {
        commentService.save(comment);
        return "redirect:/comment/get/" + comment.getBlogId();
    }
}
