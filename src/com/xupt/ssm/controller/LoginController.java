package com.xupt.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by 梁峻磊 on 2017/8/25.
 */
@Controller
public class LoginController {

    //登录
    @RequestMapping("/login")
    public String login(HttpSession session,String username, String password)throws Exception{

        //调用service惊醒身份验证

        //在session保存用户信息
        session.setAttribute("username",username);
        //重定向到上皮列表
        return "redirect:/items/queryItems.action";
    }

    //登出
    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{

        //session过期,清除session
        session.invalidate();
        return "redirect:/items/queryItems.action";
    }

}
