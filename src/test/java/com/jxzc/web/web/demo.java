package com.jxzc.web.web;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;

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
        File file = new File("D:\\usr\\local\\excel\\whd\\whd_1_2018_10_21.xls");
        if (file.exists()){
            InputStream is = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(is);//创建excel工作簿

            int numberOfSheets = wb.getNumberOfSheets();

            int activeSheetIndex = wb.getActiveSheetIndex();

            System.out.println(numberOfSheets + "_" + activeSheetIndex);

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