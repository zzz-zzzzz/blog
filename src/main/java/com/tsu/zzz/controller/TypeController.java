package com.tsu.zzz.controller;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/admin/type/page/{page}")
    public ModelAndView findByPage(@PathVariable(required = false) Integer page) {
        PageInfo<Type> pageInfo = typeService.findByPage(page, null);
        ModelAndView modelAndView = new ModelAndView("admin/types");
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @PostMapping("/admin/type/save")
    public String save(Type type) {
        typeService.save(type);
        return "redirect:/admin/type/page/1";
    }

    @GetMapping("/admin/type/get/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/type-input");
        Type type = typeService.findById(id);
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @GetMapping("/admin/type/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/admin/type/page/1";
    }

    @PostMapping("/admin/type/update/{id}")
    public String update(@PathVariable Long id, Type type) {
        type.setId(id);
        typeService.update(type);
        return "redirect:/admin/type/page/1";
    }
}
