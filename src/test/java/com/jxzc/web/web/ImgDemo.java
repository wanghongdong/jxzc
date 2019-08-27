package com.jxzc.web.web;

import com.wf.captcha.ChineseGifCaptcha;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;

/**
 * @ClassPath com.jxzc.web.web.ImgDemo
 * @ClassName ImgDemo
 * @Description 验证码
 * @Author whd
 * @Date 2019/4/25 17:29
 * @Version 1.0
 */

public class ImgDemo {

    public static void main(String[] args) {

        ChineseGifCaptcha captcha = new ChineseGifCaptcha();
        String text = captcha.text();
        System.out.println(text);
        //输出图片

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        captcha.out(os);

        byte[] bytes = os.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();

        String base64Img = encoder.encode(bytes);
        System.out.println("==============================");
        System.out.println("data:image/png;base64,"+base64Img);


    }



}
