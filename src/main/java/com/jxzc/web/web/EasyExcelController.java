package com.jxzc.web.web;

import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
import java.util.List;

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
    public Object list(HttpServletRequest request, PageBean pageBean){
        return this.query(pageBean.getPage(), pageBean.getLimit());
    }

    @RequestMapping(value = "export")
    public void export(HttpServletResponse response){
        ExcelUtil excelUtil = new ExcelUtil();
        int page = 1;
        int pageSize = 10000;
        PageBean<EasyExcel> pageDTO;
        do {
            pageDTO = this.query(page, pageSize);
            List<EasyExcel> data = pageDTO.getData();
            MultipleSheetProperty sheetProperty = new MultipleSheetProperty();
            sheetProperty.setData(data);
            Sheet sheet = new Sheet(page-1, 0);
            sheet.setSheetName("sheet" + page);
            sheetProperty.setSheet(sheet);
            excelUtil.writeWithMultipleSheel(response, sheetProperty, pageDTO.getPage() == pageDTO.getPages());
            page = pageDTO.getNextPageNo();
        } while (pageDTO.getPage() < pageDTO.getPages());

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

    private PageBean<EasyExcel> query(int pageNo, int pageSize){
        int total;
        List<EasyExcel> list;
        Page<EasyExcel> page = PageHelper.startPage(pageNo, pageSize, true);
        try {
            list = easyExcelMapper.queryAll();
            total = (int) page.getTotal();
        } finally {
            PageHelper.clearPage();
        }
        return new PageBean<>(total, list, pageNo, pageSize);
    }
}
