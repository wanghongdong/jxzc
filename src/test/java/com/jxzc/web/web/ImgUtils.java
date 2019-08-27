package com.jxzc.web.web;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @ClassPath com.jxzc.web.web.ImgUtils
 * @ClassName ImgUtils
 * @Description 验证码测试
 * @Author whd
 * @Date 2019/5/7 16:17
 * @Version 1.0
 */

public class ImgUtils {

    public static void main(String[] args) throws IOException {
        Thumbnails.of("C:\\Users\\lunan\\Pictures\\Camera Roll\\IMG_1945.JPG")
                .scale(1f)
                .outputQuality(0.1f)
                .toFile("C:\\Users\\lunan\\Pictures\\IMG_1945.JPG");
    }

}
