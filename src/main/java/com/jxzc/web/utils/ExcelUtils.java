package com.jxzc.web.utils;

import com.jxzc.web.dao.UserMapper;
import com.jxzc.web.entity.Class;
import com.jxzc.web.entity.*;
import com.jxzc.web.service.ClassService;
import com.jxzc.web.service.IndustryCategoryService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.utils
 * @ClassName: ExcelUtils
 * @Auther: wanghongdong
 * @Date: 2018/10/20 16 13
 * @Description: excel生成工具
 * */
@Component
public class ExcelUtils {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IndustryCategoryService industryCategoryService;
    @Autowired
    ClassService classService;
    @Autowired
    SystemUtils systemUtils;

    @Value("${excel.path}")
    public String path;

    private void initExcel(WorkReportBean bean, HSSFWorkbook excel) throws IOException {
        WorkReport report = bean.getReport();
        List<FilePic> pics = bean.getPics();
        //得到要添加的行业的sheet
        IndustryCategory category = industryCategoryService.queryById(report.getIndustryCategory());
        Class twoClass = classService.queryById(report.getTwoclass());

        HSSFSheet sheet = excel.getSheet(category.getName());

        Integer rowIndex = null;

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ){
            Row row = rit.next();
            for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext(); ) {
                Cell cell = cit.next();
                if (cell.getCellType()== HSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equals(twoClass.getClassname())){
                    rowIndex = cell.getRowIndex();
                }
            }
        }
        //设置字体
        HSSFFont font = excel.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        //设置单元格样式
        HSSFCellStyle style = excel.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setFont(font);
        style.setWrapText(true);
        if(rowIndex!=null){
            writeReport(excel,sheet,rowIndex,report,style,pics);
        }
        //生成的book 再次写入文件
//        writeBook(excel, report);
//        return getExcelName(report);
    }

    public String initExcels(List<WorkReportBean>  beans) throws IOException {
        WorkReport report = beans.get(0).getReport();
        HSSFWorkbook excel = this.createExcel(report);
        for (WorkReportBean bean : beans){
            initExcel(bean, excel);
        }
        //生成的book 再次写入文件
        writeBook(excel, beans.get(0).getReport());
        return getExcelName(beans.get(0).getReport());
    }

    /**
     * @Author wanghongdong
     * @Description //TODO 插入数据
     * @Date  2018/10/21 23:35
     * @Param [wb, sheet, rowIndex, report, style, pics]
     * @return void
     **/
    public void writeReport(HSSFWorkbook wb, HSSFSheet sheet, Integer rowIndex, WorkReport report, HSSFCellStyle style, List<FilePic> pics){
        HSSFRow row = sheet.getRow(rowIndex);
        HSSFCell relatedEventsCell = row.createCell(2);
        relatedEventsCell.setCellValue(new HSSFRichTextString(report.getRelatedEvents()));
        relatedEventsCell.setCellStyle(style);
        HSSFCell personalCommentsCell = row.createCell(3);
        personalCommentsCell.setCellStyle(style);
        personalCommentsCell.setCellValue(new HSSFRichTextString(report.getPersonalComments()));
        int cellIndex = 4;
        for (FilePic pic : pics) {
            setPicCell(pic, wb, sheet, rowIndex, cellIndex);
            cellIndex ++;
        }
    }

    /**
     * @Author wanghongdong
     * @Description 按行业名称创建sheet
     * @Date  2018/10/20 17:15
     * @Param [excel, sheetName, userId]
     * @return org.apache.poi.hssf.usermodel.HSSFSheet
     **/
    public HSSFSheet createSheet(HSSFWorkbook excel, String sheetName, Integer userId){
        //创建sheet
        HSSFSheet sheet = excel.createSheet(sheetName);
        //自动列宽
        sheet.autoSizeColumn((short)1);
        //计算二级分类数量
        int classNum = getClassNum(userId);
        //设置字体
        HSSFFont font = excel.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        //设置单元格样式
        HSSFCellStyle headStyle = excel.createCellStyle();
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        headStyle.setFont(font);
        headStyle.setWrapText(true);
        //设置单元格样式
        HSSFCellStyle cellStyle = excel.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        //创建表头
        createHeadRow(sheet,headStyle);
        //如果分类数量大于0
        if (classNum>0){
            //设置行索引、获取分类
            int rowIndex = 1;
            Map<Class, TreeSet<Class>> myClasses = classService.queryMyClasses(userId);
            for (Class oneClass : myClasses.keySet()) {
                //创建行
                HSSFRow row = sheet.createRow(rowIndex);
                //此一级分类的开始行索引为rowIndex
                int startRowIndex = rowIndex;
                //设置每行的第一列 为一级类别
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(oneClass.getClassname());
                cell0.setCellStyle(cellStyle);
                //获取此类别的二级分类、如果有二级分类、，并合并第一个单元格
                TreeSet<Class> twoClasses = myClasses.get(oneClass);
                if (twoClasses!=null && twoClasses.size()>0){
                    for (Class twoClass : twoClasses) {
                        //设置各二级分类的名称到每行的第二个单元格、创建后边几个单元格
                        HSSFRow row1 = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
                        row1.setHeightInPoints(70);
                        HSSFCell cell1 = row1.createCell(1);
                        cell1.setCellValue(twoClass.getClassname());
                        cell1.setCellStyle(cellStyle);
                        HSSFCell cell2 = row1.createCell(2); cell2.setCellStyle(cellStyle);
                        HSSFCell cell3 = row1.createCell(3); cell3.setCellStyle(cellStyle);
                        HSSFCell cell4 = row1.createCell(4); cell4.setCellStyle(cellStyle);
                        HSSFCell cell5 = row1.createCell(5); cell5.setCellStyle(cellStyle);
                        HSSFCell cell6 = row1.createCell(6); cell6.setCellStyle(cellStyle);
                        HSSFCell cell7 = row1.createCell(7); cell7.setCellStyle(cellStyle);
                        //行索引+1
                        rowIndex++;
                    }
                    //结束二级分类设置时的行索引
                    int endRowIndex = rowIndex -1;
                    //合并单元格
                    sheet.addMergedRegion(new Region(startRowIndex, //first row (0-based)
                            (short)0, //first column  (0-based)
                            endRowIndex, //last row (0-based)
                            (short)0  //last column  (0-based)
                    ));
                }
            }
        }
        sheet.setColumnWidth(0,256*15+184);
        sheet.setColumnWidth(1,256*40+184);
        sheet.setColumnWidth(2,256*40+184);
        sheet.setColumnWidth(3,256*40+184);
        sheet.setColumnWidth(4,256*40+184);
        sheet.setColumnWidth(5,256*40+184);
        sheet.setColumnWidth(6,256*40+184);
        sheet.setColumnWidth(7,256*40+184);
        return sheet;
    }

    /**
     * @Author wanghongdong
     * @Description 创建头部
     * @Date  2018/10/20 17:52
     * @Param sheet sheet
     * @return
     **/
    public HSSFRow createHeadRow(HSSFSheet sheet, HSSFCellStyle cellStyle){
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(105);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(new HSSFRichTextString("工作资料来源：\r\n万得-新闻\r\n万得-公告\r\n万得-报告\r\n" +
                "朝阳永续-报告\r\n财经网站（雪球）\r\n微信公众号"));
        cell0.setCellStyle(cellStyle);
        HSSFCell cell1 = row.createCell(2);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("事件");
        HSSFCell cell2 = row.createCell(3);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue("个人点评");
        HSSFCell cell3 = row.createCell(4);
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("图片");
        sheet.addMergedRegion(new Region(0, //first row (0-based)
                (short)0, //first column  (0-based)
                0, //last row (0-based)
                (short)1  //last column  (0-based)
        ));
        return row;
    }

    /**
     * @Author wanghongdong
     * @Description 获取excle 地址
     * @Date  2018/10/20 18:43
     * @Param [report]
     * @return java.lang.String
     **/
    public String getExcelPath(WorkReport report){
        //格式化时间
        String excelName = getExcelName(report);
        // 目录： ${excel.path}/whd/whd_1_2018_10_20.excel
        String excelPath = path + File.separatorChar + systemUtils.getLoginName();
        File file = new File(excelPath);
        if (!file.exists()){
            file.mkdirs();
        }
        return excelPath + File.separatorChar + excelName;
    }

    /**
     * @Author wanghongdong
     * @Description 获取excelName
     * @Date  2018/10/21 2:25
     * @Param [report]
     * @return java.lang.String
     **/
    public String getExcelName(WorkReport report){
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String format = sdf.format(report.getWorkTime());
        // 文件名格式： user.loginName_user.id_date.xls 例如： whd_1_2018_10_20.xls
        String excelName = report.getCreateName() + "_workReport" + report.getCreateId() + "_" + format + ".xls";
        return excelName;
    }

    /**
     * @Author wanghongdong
     * @Description  获取excel
     * @Date  2018/10/20 17:52
     * @Param [report]
     * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
     **/
    public HSSFWorkbook getExcel(WorkReport report) throws IOException {
        String excelPath = getExcelPath(report);
        File file = new File(excelPath);
        //读取文件
        InputStream is = new FileInputStream(file);
        HSSFWorkbook excel = new HSSFWorkbook(is);
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return excel;
    }

    //创建excel
    public HSSFWorkbook createExcel(WorkReport report) throws IOException {
        String excelPath = getExcelPath(report);
        File file = new File(excelPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        HSSFWorkbook excel = new HSSFWorkbook();
        //根据当前用户的行业类别创建所有的sheet
        List<IndustryCategory> categories = industryCategoryService.queryList(report.getCreateId());
        if(categories!=null && categories.size()>0){
            for (IndustryCategory categorie : categories) {
                //按行业名称创建sheet
                createSheet(excel, categorie.getName(), report.getCreateId());
            }
        }
        writeBook(excel, report);
        return excel;
    }

    private void writeBook(HSSFWorkbook excel, WorkReport report) throws IOException {
        String excelPath = getExcelPath(report);
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

    public void setPicCell(FilePic pic, HSSFWorkbook wb, HSSFSheet sheet, int rowIndex, int cellIndex){
        InputStream is = null;
        try {
            //图片读取
            is = new FileInputStream(pic.getFilePath());
            byte[] bytes = IOUtils.toByteArray(is);
            // 利用HSSFPatriarch将图片写入EXCEL
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            /**
             * 该构造函数有8个参数
             * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离
             * 后四个参数，前两个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum，
             * excel中的cellNum和rowNum的index都是从0开始的
             *
             */
            //图片一导出到单元格B2中
            HSSFClientAnchor anchor = new HSSFClientAnchor(1, 1, 1, 1,
                    (short) cellIndex, rowIndex, (short) (cellIndex+1), rowIndex+1);
            // 插入图片
            patriarch.createPicture(anchor, wb.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG));
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("io erorr : " + io.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public int getClassNum(Integer userId){
        Map<Class, TreeSet<Class>> classMap = classService.queryMyClasses(userId);
        int i = 0;
        if (classMap!=null && classMap.size()>0){
            for (Class oneClass : classMap.keySet()) {
                TreeSet<Class> classes = classMap.get(oneClass);
                i += classes.size();
            }
        }
        return i;
    }

    public HSSFCellStyle getCellStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        return cellStyle;
    }

    /**
     * @Author wanghongdong
     * @Description 在指定位置插入一行
     * @Date  2018/10/21 23:37
     * @Param [sheet 工作表, startRowIndex 开始的位置，合并单元格用, endRowIndex 结束的位置，合并单元格用, insertRowIndex 要插入的位置]
     * @return void
     **/
    public void insertRow(HSSFSheet sheet, Integer startRowIndex, Integer endRowIndex, Integer insertRowIndex){
        //插入一行
        sheet.shiftRows(insertRowIndex, sheet.getLastRowNum(), 1, true, true, true);
        //移动图片
        HSSFPatriarch drawingPatriarch = sheet.getDrawingPatriarch();
        if(drawingPatriarch!=null){
            List<HSSFShape> shapes = drawingPatriarch.getChildren();
            if (shapes!=null && shapes.size()>0){
                for (HSSFShape shape : shapes) {
                    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                    anchor.setAnchorType(HSSFClientAnchor.MOVE_AND_RESIZE);
                    if (anchor.getRow1()>=insertRowIndex){
                        anchor.setRow1(anchor.getRow1()+1);
                        anchor.setRow2(anchor.getRow1()+1);
                    }
                }
            }
        }
        sheet.addMergedRegion(new Region(startRowIndex, //first row (0-based)
                (short)1, //first column  (0-based)
                endRowIndex, //last row (0-based)
                (short)1  //last column  (0-based)
        ));
        sheet.getRow(insertRowIndex).setHeightInPoints(70);
    }

}
