package com.tsu.zzz.controller;

import com.tsu.zzz.pojo.User;
import com.tsu.zzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(String username,String password, HttpSession session, RedirectAttributes attributes) {
        User loginUser = userService.findByUsernameAndPassword(username,password);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            return "admin/index";
        }
        attributes.addFlashAttribute("errorMsg", "用户名或密码错误!");
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin";
    }

}
