package com.jxzc.model.utils;

import com.jxzc.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class SystemUtils {

    public static User getCurrentUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(JSConstant.SESSION_CURRENT_USER);
        return user;
    }

}
