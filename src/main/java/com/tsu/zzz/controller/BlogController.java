package com.tsu.zzz.controller;

import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        Map<String, List<Blog>> blogByYear = blogService.findBlogByYear();
        Integer blogCount = blogService.findCount();
        model.addAttribute("blogByYear", blogByYear);
        model.addAttribute("blogCount", blogCount);
        return "archives";
    }
}
