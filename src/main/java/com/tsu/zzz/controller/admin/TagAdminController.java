package com.tsu.zzz.controller.admin;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class TagAdminController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tag/page/{page}")
    public ModelAndView findByPage(@PathVariable(required = false) Integer page) {
        PageInfo<Tag> pageInfo = tagService.findByPage(page, null);
        ModelAndView modelAndView = new ModelAndView("admin/tags");
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @PostMapping("/tag/save")
    public String save(Tag tag) {
        tagService.save(tag);
        return "redirect:/admin/tag/page/" + (tagService.findCount() / 6 + 1);
    }

    @GetMapping("/tag/get/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/tag-input");
        Tag tag = tagService.findById(id);
        modelAndView.addObject("tag", tag);
        return modelAndView;
    }

    @GetMapping("/tag/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        tagService.delete(id);
        return "redirect:/admin/tag/page/1";
    }

    @PostMapping("/tag/update/{id}")
    public String update(@PathVariable Long id, Tag tag) {
        tag.setId(id);
        tagService.update(tag);
        return "redirect:/admin/tag/page/1";
    }
}
