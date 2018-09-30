package com.jxzc.model.web;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 * @Description: //**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @ClassName: TestControll
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 * @Description:
 */
@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "你好，大猪蹄子";
    }

    @RequestMapping("/index")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "index";
    }

    @RequestMapping("/demo")
    public String demo(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "index";
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;//图片
        try {
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //将图片读到BufferedImage
            bufferImg = ImageIO.read(new File("C:\\Users\\whd\\Desktop\\mmexport1537975642350.jpg"));
            // 将图片写入流中
            ImageIO.write(bufferImg, "png", byteArrayOut);
            // 创建一个工作薄
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建一个sheet
            HSSFSheet sheet = wb.createSheet("out put excel");
            // 利用HSSFPatriarch将图片写入EXCEL
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            /**
             * 该构造函数有8个参数
             * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离
             * 后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum，
             * excel中的cellNum和rowNum的index都是从0开始的
             *
             */
            //图片一导出到单元格B2中
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                    (short) 10, 1, (short) 13, 4);
            // 插入图片
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            //生成的excel文件地址
            fileOut = new FileOutputStream("C:\\Users\\whd\\Desktop\\123.xls");
            // 写入excel文件
            wb.write(fileOut);
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("io erorr : " + io.getMessage());
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
