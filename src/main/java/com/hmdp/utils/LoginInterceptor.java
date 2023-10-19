package com.hmdp.utils;

import com.hmdp.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取session
        HttpSession session = request.getSession();
        //2.获取session中的用户
        Object user = session.getAttribute("user");
        //3.判断用户是否存在
        if (user == null){
            response.setStatus(401);
            return false;
        }
        //4.存在，保存用户信息到TreadLocal中

        UserHolder.saveUser((User) user);
        //5.放行
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
