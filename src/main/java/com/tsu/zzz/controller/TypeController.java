package com.tsu.zzz.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.BlogService;
import com.tsu.zzz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Integer page, Model model) {
        List<Type> typeList = typeService.findAll();
        if (id == -1) {
            id = typeList.get(0).getId();
        }
        PageInfo<Blog> pageInfo = blogService.findByTypeId(id, page, 4);
        model.addAttribute("emptyNum",4-pageInfo.getList().size());
        model.addAttribute("typeList", typeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
