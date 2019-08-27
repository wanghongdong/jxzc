package com.jxzc.web.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.izu.ThirdRestLocator;
import com.izu.framework.web.rest.client.BaseHttpClient;
import com.izu.framework.web.rest.client.RestClient;
import com.izu.framework.web.rest.response.RestResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        File file = new File("C:\\Users\\lunan\\Desktop//city_dic.xlsx");
        if (file.exists()){
            InputStream is = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(is);//创建excel工作簿
            XSSFSheet sheetAt = wb.getSheetAt(0);
            int lastRowNum = sheetAt.getLastRowNum();
            JSONArray city = getCity();
            String s = "";
            for(Object obj : city){
                JSONObject jsonObject = (JSONObject)obj;
                String name = jsonObject.getString("Name");
                boolean isExists = false;
                for (int i = 0; i <= lastRowNum; i++) {
                    XSSFRow row1 = sheetAt.getRow(i);
                    XSSFCell idCell = row1.getCell(0);
                    XSSFCell nameCell = row1.getCell(1);
                    Double cityId = idCell!=null ? idCell.getNumericCellValue() : 0;
                    String cityName = nameCell!=null ? nameCell.getStringCellValue() : "";
                    if (cityName.startsWith(name)){
                        jsonObject.put("cityName", cityName);
                        jsonObject.put("cityId", cityId.intValue());
                        isExists = true;
                        break;
                    }
                }
                System.out.println(isExists+"，name: "+name);
                if (!isExists){
                    s+= name + ",";
                }
            }
            System.out.println(s);
            System.out.println(city.toJSONString());
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

    public static JSONArray getCity(){
        String restUrl = new ThirdRestLocator().getRestUrl("/violation/postCities");
        RestResponse response = RestClient.requestForObject(BaseHttpClient.HttpMethod.POST, restUrl, null, null, null);
        JSONArray jsonObject = (JSONArray) response.getData();
        return jsonObject;
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