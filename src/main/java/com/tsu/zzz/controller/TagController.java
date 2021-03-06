package com.tsu.zzz.controller;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.BlogService;
import com.tsu.zzz.service.TagService;
import com.tsu.zzz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Integer page, Model model) {
        List<Tag> tagList = tagService.findAll();
        if (id == -1) {
            id = tagList.get(0).getId();
        }
        PageInfo<Blog> pageInfo = blogService.findByTagId(id, page, 4);
        model.addAttribute("tagList",tagList);
        model.addAttribute("emptyNum",4-pageInfo.getList().size());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }

}
