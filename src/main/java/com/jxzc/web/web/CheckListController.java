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
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public AjaxMsg exportExcel(String arrStr, HttpServletRequest request){
        JSONArray array = JSON.parseArray(arrStr);
        List<CheckList> checkLists = checkListService.queryAll();

        Iterator<Object> iter = array.iterator();
        while(iter.hasNext()){
            JSONObject next = (JSONObject) iter.next();
            String idStr = next.getString("id");
            String[] s = idStr.split("_");
            String check = next.getString("check");
            String[] split = check.split(",");
            String id = s[1];
            String b1 =  split[0];
            String b2 =  split[1];
            String b3 =  split[2];
            for(CheckList cs : checkLists){
                if (cs.getId()==Integer.valueOf(id)){
                    cs.setB1(b1);
                    cs.setB2(b2);
                    cs.setB3(b3);
                }
            }
            String excel = getExcel(checkLists);
        }
        return AjaxMsg.success();
    }

    public String getExcel(List<CheckList> list){
        //格式化时间
        String excelPath = null;
        InputStream is = null;
        Calendar cal = Calendar.getInstance();
        int weekToday = cal.get(Calendar.DAY_OF_WEEK)-1;
        try {
            is = this.getClass().getResourceAsStream("/templates/checklist.xlsx");
            HSSFWorkbook book = new HSSFWorkbook(is);
            HSSFSheet sheet = book.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            for (int i = 2; i< lastRowNum; i++){
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell1 = row.getCell(1);
                if(cell1!=null){
                    for (CheckList cs:list) {
                        if(cs.getCheckname().equals(cell1.getStringCellValue())){
                            int b1n = 2 + weekToday -1;
                            int b2n = 3 + weekToday -1;
                            int b3n = 4 + weekToday -1;
                            row.createCell(3);
                        }
                    }
                }
            }
            writeBook(book);
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

    private void writeBook(HSSFWorkbook excel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String format = sdf.format(new Date());
        // 文件名格式： user.loginName_user.id_date.xls 例如： whd_1_2018_10_20.xls
        String excelName = systemUtils.getLoginName() + "_checkList" + "_" + format + ".xls";
        String excelPath = path + File.separatorChar + systemUtils.getLoginName();
        File file = new File(excelPath);
        if (!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(excelPath + File.separatorChar + excelName);
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
