package com.jxzc.web.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.CollectionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @ClassPath com.jxzc.web.utils.ExcelUtil
 * @ClassName ExcelUtil
 * @Description 文件导出
 * @Author whd
 * @Date 2019/8/27 20:10
 * @Version 1.0
 */
@Slf4j
@Getter
@Setter
public class ExcelUtil {

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    private OutputStream outputStream;
    private ExcelWriter excelWriter;


    /**
     * 导出excel
     * @author whd
     * @date 2019/8/27 20:12
     * @param response 请求响应
     * @param sheetProperty sheet
     * @param isLast 是否是最后一个
     * @return void
     **/
    public void writeWithMultipleSheet(HttpServletResponse response, MultipleSheetProperty sheetProperty, boolean isLast){
        if(sheetProperty==null || sheetProperty.isNull()){
            return;
        }
        ExcelWriter excelWriter = this.getExcelWriter();
        OutputStream outputStream = this.getOutputStream();
        try {
            if (outputStream==null){
                outputStream = response.getOutputStream();
                setOutputStream(outputStream);
            }
            if (excelWriter==null){
                excelWriter = EasyExcelFactory.getWriter(outputStream);
                setExcelWriter(excelWriter);
            }
            Sheet sheet = sheetProperty.getSheet() != null ? sheetProperty.getSheet() : initSheet;
            if(!CollectionUtils.isEmpty(sheetProperty.getData())){
                sheet.setClazz(sheetProperty.getData().get(0).getClass());
            }
            excelWriter.write(sheetProperty.getData(), sheet);
        }  catch (IOException e) {
            log.error("excel文件导出失败, 失败原因", e);
        } finally {
            if (isLast){
                try {
                    if(excelWriter != null){
                        excelWriter.finish();
                    }
                    if(outputStream != null){
                        outputStream.close();
                    }
                } catch (IOException e) {
                    log.error("excel文件导出失败, 失败原因", e);
                }
            }
        }
    }

//    private void flush(ExcelWriter writer){
//        try {
//            Field excelBuilderFiled = writer.getClass().getDeclaredField("excelBuilder");
//            excelBuilderFiled.setAccessible(true);
//            ExcelBuilder excelBuilder = (ExcelBuilder) excelBuilderFiled.get(excelWriter);
//            Field contextFiled = excelBuilder.getClass().getDeclaredField("context");
//            contextFiled.setAccessible(true);
//            WriteContext context = (WriteContext) contextFiled.get(excelBuilder);
//            context.getWorkbook().write(context.getOutputStream());
//        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
//            e.printStackTrace();
//            log.info("导出失败", e);
//        }
//    }

    /**
     * 获取cell值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if (cell==null){
            return "";
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                //如果为时间格式的内容
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellValue=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                } else {
                    cellValue = new DecimalFormat("#").format(cell.getNumericCellValue());
                }
                break;
            case STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }
}
