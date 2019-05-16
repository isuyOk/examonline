package com.manager.config;

import com.manager.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class MyIntercepter implements HandlerInterceptor {

    /**
     * 进入controller层之前拦截请求
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html");
        Cookie[] cookies=request.getCookies();
        if (cookies!=null){
            User user=new User();
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("email_cookie")){
                    user.setEmail(cookie.getValue());
                }else if (cookie.getName().equals("password_cookie")){
                    user.setPassword(cookie.getValue());
                }else {
                    //重新登录
                }
            }
            request.setAttribute("user","user");
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
