package com.manager.controller;

import com.manager.mapper.UserMapper;
import com.manager.model.User;
import com.manager.result.CodeMsg;
import com.manager.result.Result;
import com.manager.service.ServiceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;
    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result doLogin(HttpServletRequest request, HttpServletResponse response, String email, String password){
        if (email==null||email.equals("")){
            return Result.error(CodeMsg.EMAIL_EMPTY);
        }
        if (password==null||password.equals("")){
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
       User user= loginService.checkUser(email,password);
        if (user!=null){
            response.setContentType("text/html");
            Cookie email_cookie=new Cookie("email_cookie",user.getEmail());
            Cookie password_cookie=new Cookie("password_cookie",user.getPassword());
            email_cookie.setMaxAge(60);
            email_cookie.setPath("/");
            password_cookie.setMaxAge(60*3*24);
            password_cookie.setPath("/");
            response.addCookie(email_cookie);
            response.addCookie(password_cookie);
            return Result.success(CodeMsg.SUCCESS);
        }else {
            return Result.error(CodeMsg.USER_NOT_EXIST);
        }
    }


}
