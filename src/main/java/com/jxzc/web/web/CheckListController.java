package com.jxzc.web.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxzc.web.bean.AjaxMsg;
import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.CheckList;
import com.jxzc.web.service.CheckListService;
import com.jxzc.web.utils.SystemUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/checkList")
public class CheckListController {

    private static Logger logger = LoggerFactory.getLogger(CheckListController.class);

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
        Calendar cal = Calendar.getInstance();
        int weekToday = cal.get(Calendar.DAY_OF_WEEK)-1;
        if (weekToday==0||weekToday==6){
            return AjaxMsg.error("非工作日时段，不能导出checkList！");
        }
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
                if (cs.getId().equals(Integer.valueOf(id))){
                    cs.setB1(b1);
                    cs.setB2(b2);
                    cs.setB3(b3);
                }
            }
        }
        HSSFWorkbook workbook = initWorkBook();
        if (workbook == null){
            return AjaxMsg.error("创建失败！");
        }
        String s1 = this.create(checkLists,workbook);
        Map<String,Object> map = new HashMap<>();
        map.put("excelName",s1);
        return AjaxMsg.success("创建成功！",map);
    }

    @RequestMapping(value = "/excelDown")
    public void download(String excelName, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(getExcelPath()+ File.separatorChar + excelName));
            outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + getExcelName());
            response.setCharacterEncoding("utf-8");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String create(List<CheckList> list, HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIME.getIndex());// 设置背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle trueStyle = style;
        Calendar cal = Calendar.getInstance();
        int weekToday = cal.get(Calendar.DAY_OF_WEEK)-1;
        HSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i< lastRowNum-1; i++){
            HSSFRow row = sheet.getRow(i);
            if (row!=null){
                HSSFCell cell1 = row.getCell(1);
                if(cell1!=null){
                    for (CheckList cs:list) {
                        if(cs.getCheckname().equals(cell1.getStringCellValue())){
                            int b1n = 3 * weekToday -1;
                            int b2n = 3 * weekToday;
                            int b3n = 3 * weekToday +1;
                            HSSFCell cellb1 = row.createCell(b1n);
                            if("true".equals(cs.getB1())){
                                cellb1.setCellStyle(trueStyle);
                            }
                            HSSFCell cellb2 = row.createCell(b2n);
                            if("true".equals(cs.getB2())){
                                cellb2.setCellStyle(trueStyle);
                            }
                            HSSFCell cellb3 = row.createCell(b3n);
                            if("true".equals(cs.getB3())){
                                cellb3.setCellStyle(trueStyle);
                            }
                        }
                    }
                }
            }
        }
        writeBook(workbook);
        return getExcelName();
    }

    private String getExcelPath() {
        return path + File.separatorChar + systemUtils.getLoginName();
    }


    /**
     * 获取workBook
     * @return
     */
    private HSSFWorkbook initWorkBook() {
        InputStream is = null;
        String excelPath = getExcelPath();
        String excelName = getExcelName();
        HSSFWorkbook workbook = null;
        try {
            File file = new File(excelPath);
            if (!file.exists()){
                file.mkdirs();
            }
            file = new File(excelPath + File.separatorChar + excelName);
            if (!file.exists()){
                is = this.getClass().getResourceAsStream("/templates/checklist.xls");
            }else{
                is = new FileInputStream(file);
            }
            workbook = new HSSFWorkbook(is);
            writeBook(workbook);
            logger.info("写出excel完成");
            logger.info("excelName：{}，excelPath：{},",excelName,excelPath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("CheckListExcelUtil init error: ", e);
        } finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    is=null;
                }
            }
        }
        return workbook;
    }

    /**
     * 获取checkList 的文件名称
     * @return
     */
    public String getExcelName(){
        Calendar cal = Calendar.getInstance();
        int weeksInWeekYear = cal.getWeeksInWeekYear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
        String format = sdf.format(new Date());
        String excelName = systemUtils.getLoginName() + "_checkList" + "_" + format + "_" + weeksInWeekYear + ".xls";
        return excelName;
    }

    /**
     * 写出excel
     * @param excel
     * @throws IOException
     */
    private void writeBook(HSSFWorkbook excel){
        File file = new File(getExcelPath());
        if (!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(getExcelPath() + File.separatorChar + getExcelName());
            excel.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
