package com.jxzc.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @ClassPath com.jxzc.web.web.CharsetController
 * @ClassName CharsetController
 * @Description TODO
 * @Author whd
 * @Date 2019/4/29 20:37
 * @Version 1.0
 */
@Controller
public class CharsetController {

        @RequestMapping("index/charset")
    public void ast(String str, HttpServletRequest request) throws UnsupportedEncodingException {

        String characterEncoding = request.getCharacterEncoding();

        System.out.println(characterEncoding);

        System.out.println(str);

        System.out.println("iso8859-1:"+new String(str.getBytes("iso8859-1"), "iso8859-1"));

        System.out.println("utf-8:"+new String(str.getBytes("utf-8"), "utf-8"));

        System.out.println("GBK:"+new String(str.getBytes("GBK"), "GBK"));
    }


}
