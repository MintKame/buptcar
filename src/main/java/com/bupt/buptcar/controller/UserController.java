package com.bupt.buptcar.controller;

import com.bupt.buptcar.pojo.User;
import com.bupt.buptcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /** 去登录页面 */
    @GetMapping("/login")
    String index(HttpSession session){
        Object user = session.getAttribute("user");
        return "login";
    }

    /** 登录账号 */
    @PostMapping("/login")
    String login(Model model, HttpSession session, User userMsg){
        User user = userService.login(userMsg);
        if (user != null){
            session.setAttribute("user", user);
            return "redirect:car";
        } else {
            model.addAttribute("msg", "登陆信息错误");
            return "login";
        }
    }

    /** 去注册页面 */
    @GetMapping("/register")
    String goRegister(HttpSession session){
        Object user = session.getAttribute("user");
        return "register";
    }

    /** 注册账号 */
    @PostMapping("/register")
    String register(Model model, User userMsg){
        String msg = userService.register(userMsg);
        model.addAttribute("msg", msg);
        return "register";
    }
}
