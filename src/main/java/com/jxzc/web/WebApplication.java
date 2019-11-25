package com.jxzc.web;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication()
@ServletComponentScan//Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册
@EnableAsync//开启多线程
@EnableTransactionManagement//开启事务支持
@MapperScan("com.jxzc.web.dao")
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new LoginFilter());//添加过滤器
//        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.addInitParameter("name", "alue");//添加默认参数
//        registration.setName("MyFilter");//设置优先级
//        registration.setOrder(1);//设置优先级
//        return registration;
//    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        p.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(p);
        return pageHelper;
    }
}