package com.tsu.zzz.controller;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.pojo.Comment;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.BlogService;
import com.tsu.zzz.service.CommentService;
import com.tsu.zzz.service.TagService;
import com.tsu.zzz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Integer page) {
        Blog blog = new Blog();
        blog.setPublished(true);
        PageInfo<Blog> pageInfo = blogService.findByPage(page, 4, blog);
        List<Type> typeList = typeService.findAll(4);
        List<Tag> tagList = tagService.findAll(10);
        List<Blog> latestBlogList = blogService.findByCreateTimeDesc(3);
        model.addAttribute("emptyNum", 4 - pageInfo.getList().size());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tagList", tagList);
        model.addAttribute("typeList", typeList);
        session.setAttribute("latestBlogList", latestBlogList);
        return "index";
    }

    @GetMapping("/search/{page}")
    public String search(@RequestParam String query, Model model, @PathVariable Integer page) {
        Blog blog = new Blog();
        blog.setTitle(query);
        blog.setDescription(query);
        PageInfo<Blog> pageInfo = blogService.findByTitleOrDescription(page, 4, blog);
        model.addAttribute("emptyNum", 4 - pageInfo.getList().size());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findByIdAndConvertHtml(id);
        List<Comment> commentList = commentService.findByBlogId(id);
        blogService.updateViews(id);
        model.addAttribute("blog", blog);
        model.addAttribute("commentList", commentList);
        return "blog";
    }
}
