package com.tsu.zzz.controller.admin;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/blog/page/{page}")
    public String findByPage(@PathVariable Integer page, Blog blog, Model model) {
        List<Type> typeList = typeService.findAll();
        PageInfo<Blog> pageInfo = blogService.findByPage(page, null, blog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("typeList", typeList);
        return "admin/blogs";
    }

    @PostMapping("/blog/page/ajax")
    public String findByPageAjax(Integer page, Blog blog, Model model) {
        PageInfo<Blog> pageInfo = blogService.findByPage(page, null, blog);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blog-input/{page}")
    public String blogInput(@PathVariable Integer page, Model model) {
        List<Type> typeList = typeService.findAll();
        List<Tag> tagList = tagService.findAll();
        model.addAttribute("typeList", typeList);
        model.addAttribute("tagList", tagList);
        model.addAttribute("page", page);
        return "admin/input";
    }

    @PostMapping("/blog/save")
    public String save(Blog blog, @RequestParam(value = "tagIds") List<Long> tagIds, Integer page, RedirectAttributes attributes) {
        if (blogService.findByTitle(blog.getTitle()) == null) {
            blogService.save(blog, tagIds);
            attributes.addFlashAttribute("message", "新建博客成功");
            Integer count = blogService.findCount();
            return "redirect:/admin/blog/page/" + (count % 6 == 0 ? count / 6 : count / 6 + 1);
        } else {
            attributes.addFlashAttribute("message", "已经存在标题为" + blog.getTitle() + "的博客，添加失败");
            return "redirect:/admin/blog-input/" + page;
        }

    }

    @GetMapping("/blog/get/{id}")
    public ModelAndView getById(@PathVariable Long id, Integer page) {
        ModelAndView modelAndView = new ModelAndView("admin/input");
        Blog blog = blogService.findById(id);
        List<Type> typeList = typeService.findAll();
        List<Tag> tagList = tagService.findAll();
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("page", page);
        modelAndView.addObject("typeList", typeList);
        modelAndView.addObject("tagList", tagList);
        return modelAndView;
    }

    @PostMapping("/blog/update/{id}")
    public String update(Blog blog, @RequestParam(value = "tagIds") List<Long> tagIds, Integer page, RedirectAttributes attributes, @PathVariable Long id) {
        Blog queryBlog = blogService.findByTitle(blog.getTitle());
        if (queryBlog != null && queryBlog.getId() != id) {
            attributes.addFlashAttribute("message", "已经存在标题为" + blog.getTitle() + "的博客，修改失败");
            return "redirect:/admin/blog/get/" + id + "/?page=" + page;
        }
        blog.setId(id);
        blogService.update(blog, tagIds);
        attributes.addFlashAttribute("message", "修改博客成功");
        return "redirect:/admin/blog/page/" + page;
    }

    @GetMapping("/blog/delete/{id}")
    public String delete(RedirectAttributes attributes, @PathVariable Long id) {
        blogService.delete(id);
        attributes.addFlashAttribute("message", "删除博客成功");
        return "redirect:/admin/blog/page/1";
    }
}

