package com.jxzc.web.web;

import com.jxzc.web.bean.PageBean;
import com.jxzc.web.dao.EasyExcelMapper;
import com.jxzc.web.entity.EasyExcel;
import com.jxzc.web.excel.ExcelUtil;
import com.jxzc.web.excel.MultipleSheetProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassPath com.jxzc.web.web.EasyExcelController
 * @ClassName EasyExcelController
 * @Description EasyExcelController
 * @Author whd
 * @Date 2019/8/27 20:17
 * @Version 1.0
 */
@RequestMapping("easyExcel")
@Controller
public class EasyExcelController {

    @Autowired
    EasyExcelMapper easyExcelMapper;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return "easyExcel/list";
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request){
        return new PageBean();
    }

    @RequestMapping(value = "export")
    public void export(HttpServletResponse response){

        ExcelUtil excelUtil = new ExcelUtil();

        MultipleSheetProperty sheetProperty = new MultipleSheetProperty();
//        excelUtil.writeWithMultipleSheel(response, );

    }

    @ResponseBody
    @RequestMapping("batchInsert")
    public Object batchInsert(){
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            EasyExcel easyExcel = new EasyExcel();
            easyExcel.setMoney((double) i);
            easyExcel.setName("test"+i);
            easyExcelMapper.insertSelective(easyExcel);
        }
        return System.currentTimeMillis() - l;
    }

}
