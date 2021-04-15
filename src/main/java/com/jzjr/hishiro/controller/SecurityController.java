package com.jzjr.hishiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/")
public class SecurityController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        //判断是否登录成功
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null) {
            return "欢迎你\t" + subject.getPrincipal();
        }
        //获取登录失败的原因
        String failMsg = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String msg = "";
        if (UnknownAccountException.class.getName().equals(failMsg)) {
            msg = "账号不存在";
        } else if (IncorrectCredentialsException.class.getName().equals(failMsg)) {
            msg = "密码不正确";
        } else if (LockedAccountException.class.getName().equals(failMsg)) {
            msg = "账号被锁定";
        } else if (ExpiredCredentialsException.class.getName().equals(failMsg)) {
            msg = "账号已过期";
        } else {
            msg = "未知";
            logger.error("[login][未知登录错误：{}]", failMsg);
        }
        return "登录失败，原因：" + msg;
    }

    @ResponseBody
    @GetMapping("/login_success")
    public String loginSuccess() {
        return "登录成功";
    }

    @ResponseBody
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "缺少权限";
    }
}
