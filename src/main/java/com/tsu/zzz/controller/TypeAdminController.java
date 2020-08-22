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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeAdminController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/type/page/{page}")
    public ModelAndView findByPage(@PathVariable(required = false) Integer page) {
        PageInfo<Type> pageInfo = typeService.findByPage(page, null);
        ModelAndView modelAndView = new ModelAndView("admin/types");
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @PostMapping("/type/save")
    public String save(Type type, RedirectAttributes attributes, Integer page) {
        if (typeService.findByName(type.getName()) == null) {
            typeService.save(type);
            attributes.addFlashAttribute("message", "分类" + type.getName() + "添加成功");
            Integer count = typeService.findCount();
            return "redirect:/admin/type/page/" + (count % 6 == 0 ? count / 6 : count / 6 + 1);
        } else {
            attributes.addFlashAttribute("message", "已经存在分类" + type.getName() + "，添加失败");
            return "redirect:/admin/type-input/" + page;
        }


    }

    @GetMapping("/type/get/{id}")
    public ModelAndView getById(@PathVariable Long id, String page) {
        ModelAndView modelAndView = new ModelAndView("admin/type-input");
        Type type = typeService.findById(id);
        modelAndView.addObject("type", type);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @GetMapping("/type/delete/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.delete(id);
        attributes.addFlashAttribute("message", "删除分类成功");
        return "redirect:/admin/type/page/1";
    }

    @PostMapping("/type/update/{id}")
    public String update(@PathVariable Long id, Type type, Integer page, RedirectAttributes attributes) {
        if (typeService.findByName(type.getName()) == null) {
            type.setId(id);
            typeService.update(type);
            attributes.addFlashAttribute("message", "分类修改成功");
            return "redirect:/admin/type/page/" + page;
        } else {
            attributes.addFlashAttribute("message", "已经存在分类" + type.getName() + "，修改失败");
            attributes.addFlashAttribute("type", typeService.findById(id));
            return "redirect:/admin/type-input/" + page;
        }

    }

    @GetMapping("/type-input/{page}")
    public String typeInput(@PathVariable Integer page, Model model) {
        model.addAttribute("page", page);
        return "admin/type-input";
    }

}
