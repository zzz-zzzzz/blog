package com.tsu.zzz.controller.admin;

import com.github.pagehelper.PageInfo;
import com.tsu.zzz.pojo.Tag;
import com.tsu.zzz.pojo.Type;
import com.tsu.zzz.service.TagService;
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
    public String save(Tag tag, Integer page, RedirectAttributes attributes) {
        if (tagService.findByName(tag.getName()) == null) {
            tagService.save(tag);
            attributes.addFlashAttribute("message", "标签" + tag.getName() + "添加成功");
            Integer count = tagService.findCount();
            return "redirect:/admin/tag/page/" + (count % 6 == 0 ? count / 6 : count / 6 + 1);
        } else {
            attributes.addFlashAttribute("message", "已经存在标签" + tag.getName() + "，添加失败");
            return "redirect:/admin/tag-input/" + page;
        }


    }

    @GetMapping("/tag/get/{id}")
    public ModelAndView getById(@PathVariable Long id, Integer page) {
        ModelAndView modelAndView = new ModelAndView("admin/tag-input");
        Tag tag = tagService.findById(id);
        modelAndView.addObject("tag", tag);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @GetMapping("/tag/delete/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.delete(id);
        attributes.addFlashAttribute("message", "标签删除成功");
        return "redirect:/admin/tag/page/1";
    }

    @PostMapping("/tag/update/{id}")
    public String update(@PathVariable Long id, Tag tag, Integer page, RedirectAttributes attributes) {
        if (tagService.findByName(tag.getName()) == null) {
            tag.setId(id);
            tagService.update(tag);
            attributes.addFlashAttribute("message", "标签修改成功");
            return "redirect:/admin/tag/page/" + page;
        } else {
            attributes.addFlashAttribute("message", "已经存在标签" + tag.getName() + "，修改失败");
            attributes.addFlashAttribute("tag", tagService.findById(id));
            return "redirect:/admin/tag-input/" + page;
        }
    }

    @GetMapping("/tag-input/{page}")
    public String tagInput(@PathVariable Integer page, Model model) {
        model.addAttribute("page", page);
        return "admin/tag-input";
    }

}
