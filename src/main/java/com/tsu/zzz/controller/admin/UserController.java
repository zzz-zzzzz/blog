package com.tsu.zzz.controller.admin;

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
    public String login(String username,String password, HttpSession session, Model model) {
        User loginUser = userService.findByUsernameAndPassword(username,password);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            return "admin/index";
        }
        model.addAttribute("errorMsg", "用户名或密码错误!");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "admin/login";
    }

}
