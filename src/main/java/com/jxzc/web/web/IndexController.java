package com.jxzc.web.web;

import com.jxzc.web.bean.AjaxMsg;
import com.jxzc.web.dao.UserMapper;
import com.jxzc.web.entity.User;
import com.jxzc.web.utils.JSConstant;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web
 * @ClassName: IndexController
 * @Auther: wanghongdong
 * @Date: 2018/11/11 23 04
 * @Description:
 * */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "login";
    }

    @RequestMapping(value = "/getCaptchaImg", method = RequestMethod.GET)
    public void getCaptchaImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session，存储验证码文本
        HttpSession session = request.getSession();
        //key
        String captchaKey = JSConstant.SESSION_CURRENT_CAPTCHA;
        //实例一个汉字Gif验证码
        ChineseGifCaptcha captcha = new ChineseGifCaptcha();
        String text = captcha.text();
        session.removeAttribute(captchaKey);
        session.setAttribute(captchaKey, text);
        //输出图片
        CaptchaUtil.setHeader(response);
        captcha.out(response.getOutputStream());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(HttpServletRequest request) {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        //key
        String captchaKey = JSConstant.SESSION_CURRENT_CAPTCHA;

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
            return AjaxMsg.error("用户名和密码不能为空");
        }
        if (StringUtils.isEmpty(captcha)){
            return AjaxMsg.error("验证码不能为空");
        }
        String captchaSe = (String) request.getSession().getAttribute(captchaKey);
        if (!captcha.equals(captchaSe)) {
            return AjaxMsg.error("验证码不正确");
        }
        User user = userMapper.selectUserByLoginName(loginName);
        if (user !=null && user.getPassword().equals(password)){
            request.getSession().setAttribute("menuNum", 1);
            request.getSession().setAttribute(JSConstant.SESSION_CURRENT_USER,user);
            request.getSession().setMaxInactiveInterval(2*60*60);
            return AjaxMsg.success("登录成功");
        }else {
            return AjaxMsg.error("用户名或密码不正确");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String,Object> map) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(HttpServletRequest request, User user) {
        User user1 = userMapper.selectUserByLoginName(user.getLoginName());
        if (user1!=null){
            return AjaxMsg.error("账号已存在，请重新注册！");
        }
        int i = userMapper.insertSelective(user);
        if (i==1){
            Map<String,Object> map = new HashMap<>();
            map.put("user",user);
            return AjaxMsg.success("注册成功！",map);
        }else {
            return AjaxMsg.error("注册失败！");
        }
    }

    @RequestMapping(value = "/verify")
    @ResponseBody
    public Object verify(String loginName) {
        User user = userMapper.selectUserByLoginName(loginName);
        if (user!=null){
            return AjaxMsg.error("账号已存在，请重新注册！");
        }
        return AjaxMsg.success();
    }

}
