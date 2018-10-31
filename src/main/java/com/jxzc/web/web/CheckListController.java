package com.jxzc.web.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxzc.web.bean.AjaxMsg;
import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.CheckList;
import com.jxzc.web.entity.IndustryCategory;
import com.jxzc.web.entity.WorkReport;
import com.jxzc.web.service.CheckListService;
import com.jxzc.web.utils.SystemUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/checkList")
public class CheckListController {

    @Value("${excel.path}")
    public String path;
    @Autowired
    CheckListService checkListService;
    @Autowired
    SystemUtils systemUtils;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(ModelMap map){
        Map<CheckList, List<CheckList>> listMap = checkListService.queryAllMap();
        String s = JSON.toJSONString(listMap);
        map.addAttribute("listMap",listMap);
        return "checkList/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageBean listPost(PageBean pageBean){
        pageBean = checkListService.queryList(pageBean);
        return pageBean;
    }


    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMsg exportExcel(String arrStr){
        JSONArray array = JSON.parseArray(arrStr);
        Iterator<Object> iter = array.iterator();
        while(iter.hasNext()){
            JSONObject next = (JSONObject) iter.next();
            String idStr = next.getString("id");
            String[] s = idStr.split("_");
            String pid = s[0];
            String id = s[1];
            String check = next.getString("check");
        }
        return AjaxMsg.success();
    }

    public String getExcel(){
        InputStream is = null;
        //格式化时间
        String excelPath = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
            String format = sdf.format(new Date());
            // 文件名格式： user.loginName_user.id_date.xls 例如： whd_1_2018_10_20.xls
            String excelName = systemUtils.getLoginName() + "_checkList" + "_" + format + ".xls";
            // 目录： ${excel.path}/whd/whd_1_2018_10_20.excel
            excelPath = path + File.separatorChar + systemUtils.getLoginName();
            File file = new File(excelPath);
            if (!file.exists()){
                file.mkdirs();
            }
            //创建文件目录
            HSSFWorkbook excel = new HSSFWorkbook();
            HSSFSheet sheet = excel.createSheet("checkList");

            sheet.createRow()





            writeBook(excel, excelPath + File.separatorChar + excelName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelPath;
    }

    private void writeBook(HSSFWorkbook excel, String excelPath) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(excelPath);
        excel.write(fileOut);
        if (fileOut != null) {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
