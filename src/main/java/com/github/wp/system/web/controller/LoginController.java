package com.github.wp.system.web.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录Controller
 * @author wangping
 * @version 1.0
 * @since 2014年2月15日, 下午5:53:43
 */
@Controller
public class LoginController {

    /**
     * 用户登录请求
     * @param req
     * @param model
     * @return
     * @author wangping
     */
    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        if(req.getParameter("forceLogout") != null) {
            model.addAttribute("error", "您已经被管理员强制退出，请重新登录");
        }
        return "login";
    }


}
