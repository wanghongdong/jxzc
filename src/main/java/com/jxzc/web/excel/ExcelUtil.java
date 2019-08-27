package com.jxzc.web.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassPath com.jxzc.web.utils.ExcelUtil
 * @ClassName ExcelUtil
 * @Description 文件导出
 * @Author whd
 * @Date 2019/8/27 20:10
 * @Version 1.0
 */
@Slf4j
public class ExcelUtil {

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    private OutputStream outputStream = null;
    private ExcelWriter excelWriter = null;


    /**
     * 导出excel
     * @author whd
     * @date 2019/8/27 20:12
     * @param response 请求响应
     * @param sheetProperty sheet
     * @param isLast 是否是最后一个
     * @return void
     **/
    public void writeWithMultipleSheel(HttpServletResponse response, MultipleSheetProperty sheetProperty, boolean isLast){
        if(sheetProperty==null || sheetProperty.isNull()){
            return;
        }
        try {
            if (this.outputStream==null){
                this.outputStream = response.getOutputStream();
            }
            if (this.excelWriter==null){
                this.excelWriter = EasyExcelFactory.getWriter(this.outputStream);
            }
            Sheet sheet = sheetProperty.getSheet() != null ? sheetProperty.getSheet() : initSheet;
            if(!CollectionUtils.isEmpty(sheetProperty.getData())){
                sheet.setClazz(sheetProperty.getData().get(0).getClass());
            }
            this.excelWriter.write(sheetProperty.getData(), sheet);
        }  catch (IOException e) {
            log.error("excel文件导出失败, 失败原因", e);
        }finally {
            if (isLast){
                try {
                    if(this.excelWriter != null){
                        this.excelWriter.finish();
                    }
                    if(this.outputStream != null){
                        this.outputStream.close();
                    }
                } catch (IOException e) {
                    log.error("excel文件导出失败, 失败原因", e);
                }
            }
        }
    }

}
