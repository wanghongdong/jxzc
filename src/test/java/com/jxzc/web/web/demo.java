package com.jxzc.web.web;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.web
 * @ClassName: demo
 * @Auther: wanghongdong
 * @Date: 2018/10/20 17 32
 * @Description:
 * */
public class demo {
    /** 测试使用的POI版本是3.1
     * @param args
     */
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\inspection_project.xlsx");
        if (file.exists()){
            InputStream is = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(is);//创建excel工作簿
            XSSFSheet sheetAt = wb.getSheetAt(0);
            int lastRowNum = sheetAt.getLastRowNum();
            System.out.println("lastRowNum   "+lastRowNum);
            XSSFRow row = sheetAt.getRow(0);
            short lastCellNum = row.getLastCellNum();
            System.out.println("lastCellNum  "+lastCellNum);
//            for (int i = 0; i <= lastRowNum; i++) {
//                sheetAt.getR
//            }
//            FileOutputStream fileOut = new FileOutputStream(file);
//            wb.write(fileOut);
//            if (fileOut != null) {
//                try {
//                    fileOut.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }


    public static Map<String, PictureData> getSheetPictrues03(int sheetNum, HSSFSheet sheet, HSSFWorkbook workbook) {

        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        List<HSSFPictureData> pictures = workbook.getAllPictures();
        if (pictures.size() != 0) {
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;

                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = pictures.get(pictureIndex);
                    String picIndex = String.valueOf(sheetNum) + "_"
                            + String.valueOf(anchor.getRow1()) + "_"
                            + String.valueOf(anchor.getCol1());
                    sheetIndexPicMap.put(picIndex, picData);
                }
            }
            return sheetIndexPicMap;
        } else {
            return null;
        }
    }
}