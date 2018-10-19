package com.jxzc.model.web;

import com.jxzc.model.bean.AjaxMsg;
import com.jxzc.model.bean.FileInfo;
import com.jxzc.model.entity.FilePic;
import com.jxzc.model.service.FilePicService;
import com.jxzc.model.utils.SystemUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 28
 * @Description: //**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @ClassName: FileController
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 28
 * @Description:
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.path}")
    private String path;

    @Autowired
    public FilePicService filePicService;

    @PostMapping
    public AjaxMsg upload(MultipartFile file,HttpServletRequest request){
        if(file==null || file.isEmpty()){
            return AjaxMsg.error("上传失败：文件不能为空！");
        }
        if (file.getSize()>2*1024*1024){
            return AjaxMsg.error("上传失败：文件大小不能超过2MB！");
        }
        String filePath = path + File.separatorChar + SystemUtils.getCurrentUser(request).getLoginname();
        long time = new Date().getTime();
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."), filename.length());
        File localFile = new File(filePath, time+fileType);
        if (!localFile.getParentFile().exists()){
            localFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(localFile);
            FilePic pic = new FilePic();
            pic.setFileName(filename);
            pic.setFilePath(localFile.getPath());
            filePicService.insert(pic);
            Map<String,Object> map = new HashMap<>();
            map.put("path",pic.getFilePath());
            map.put("id",pic.getId());
            return AjaxMsg.success("上传成功！",map);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxMsg.error("上传失败！");
        }
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(path, id + ".jpg"));
            outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + id + ".jpg");

            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
