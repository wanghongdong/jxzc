package com.jxzc.web.web;

import com.jxzc.web.bean.AjaxMsg;
import com.jxzc.web.entity.FilePic;
import com.jxzc.web.service.FilePicService;
import com.jxzc.web.utils.SystemUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.web
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 28
 * @Description: //**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.web
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
        String filename = file.getOriginalFilename();
        if (filename.length()>100){
            return AjaxMsg.error("上传失败：文件名称不能大于100个字符！");
        }
        String filePath = path + File.separatorChar + SystemUtils.getCurrentUser(request).getLoginName();
        long time = new Date().getTime();
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
            pic.setFormatName(fileType);
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

    @GetMapping("/{fileName}")
    public void download(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String filePath = path + File.separatorChar + SystemUtils.getCurrentUser(request).getLoginName() + File.separatorChar + fileName;
            inputStream = new FileInputStream(new File(filePath));
            outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
