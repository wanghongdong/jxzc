package com.jxzc.web.utils;

import com.jxzc.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SystemUtils {

    @Autowired
    private HttpServletRequest request;

    public static User getCurrentUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(JSConstant.SESSION_CURRENT_USER);
        return user;
    }

    public User getUser(){
        User user = (User) request.getSession().getAttribute(JSConstant.SESSION_CURRENT_USER);
        return user;
    }

    public String getLoginName(){
        User user = (User) request.getSession().getAttribute(JSConstant.SESSION_CURRENT_USER);
        return user.getLoginName();
    }

}
