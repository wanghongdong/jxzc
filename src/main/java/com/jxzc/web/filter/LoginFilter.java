package com.jxzc.web.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ProjectName: jsjz
 * @PackageName: com.jxzc.web.filter
 * @ClassName: LoginFilter
 * @Auther: wanghongdong
 * @Date: 2018/10/14 20 02
 * @Description: 登录拦截
 * */
@Configuration
@Component //这个注解的目的是将LoginFilter交给容器来处理。也就是让LoginFilter起作用
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String path = request.getServletPath();
//        if (!StringUtils.isEmpty(path) && (path.startsWith("/index") || path.startsWith("/css") || path.startsWith("/js"))){
//            filterChain.doFilter(servletRequest,servletResponse);
//            return;
//        }
//        User user = (User) request.getSession().getAttribute(JSConstant.SESSION_CURRENT_USER);
//        if (user==null){
//            response.sendRedirect("/index/login");
//            return;
//        }else{
            filterChain.doFilter(servletRequest,servletResponse);
//        }
    }

    @Override
    public void destroy() {

    }
}
