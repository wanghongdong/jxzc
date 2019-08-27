package com.jxzc.web.web;

import com.izu.framework.web.rest.response.PageDTO;
import com.jxzc.web.excel.ExcelUtil;
import com.jxzc.web.excel.MultipleSheetProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassPath com.jxzc.web.web.EasyExcelController
 * @ClassName EasyExcelController
 * @Description TODO
 * @Author whd
 * @Date 2019/8/27 20:17
 * @Version 1.0
 */
@RequestMapping("easyExcel")
public class EasyExcelController {

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return "easyExcel/list";
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request){
        return new PageDTO();
    }

    @RequestMapping(value = "export")
    public void export(HttpServletResponse response){

        ExcelUtil excelUtil = new ExcelUtil();

        MultipleSheetProperty sheetProperty = new MultipleSheetProperty();
//        excelUtil.writeWithMultipleSheel(response, );

    }

}
