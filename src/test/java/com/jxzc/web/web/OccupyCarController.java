package com.jxzc.web.web;

import com.alibaba.fastjson.JSON;
import com.izu.framework.web.rest.client.BaseHttpClient;
import com.izu.framework.web.rest.client.RestClient;
import com.izu.framework.web.rest.response.RestResponse;
import com.izu.stock.dto.stock.CarDynamicStockDTO;
import com.jxzc.web.dao.CarInfoMapper;
import com.jxzc.web.entity.CarInfo;
import com.jxzc.web.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassPath com.jxzc.web.web.OccupyCarController
 * @ClassName OccupyCarController
 * @Description 占车
 * @Author whd
 * @Date 2019/11/25 10:18
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class OccupyCarController {

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Test
    public void test(){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\lunan\\Desktop\\548报卖车11.21（城市已确认）.xlsx");
            XSSFSheet sheetAt = workbook.getSheetAt(1);
            for (int i = 1; i < 549; i++) {
                XSSFRow row = sheetAt.getRow(i);
                if (row!=null){
                    String cellValue = ExcelUtil.getCellValue(row.getCell(1));
                    log.info("第{}行，cellValue={}", i, cellValue);
                    CarInfo carInfo = carInfoMapper.selectByVehicleLicense(cellValue);
                    if (carInfo!=null){
                        boolean occupy = occupyCar(carInfo);
                        log.info("库存占用，{}", occupy);
                    }else{
                        log.info("车辆信息不存在，cellValue={}", cellValue);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String HOST = "http://prd-stock.izuche.com/stock-core/";

    public static final String path = "api/stock/";

    public boolean isOccupy(CarInfo carInfo){
        String url = HOST + path + "carStockForPage";
        Map<String, Object> param = new HashMap<>();
        param.put("carId", carInfo.getCarId());
        param.put("beginTime", "2019-11-25 00:00:00");
        param.put("endTime", "2019-12-25 00:00:00");
        RestResponse response = RestClient.requestForList(BaseHttpClient.HttpMethod.POST, url, param, null, CarDynamicStockDTO.class);
        log.info("查询库存信息，carId={}，response={}", carInfo.getCarId(), JSON.toJSONString(response));
        if (response!=null && response.isSuccess() && response.getData()!=null){
            List<CarDynamicStockDTO> data = (List<CarDynamicStockDTO>) response.getData();
            if (data.size()>0){
                CarDynamicStockDTO carDynamicStockDTO = data.get(0);
                return carDynamicStockDTO.getCarInfoStatus()==1;
            }
        }
        return true;
    }

    public boolean occupyCar(CarInfo carInfo){
        String url = HOST + path + "editOneCarStock";
        Map<String, Object> param = new HashMap<>();
        param.put("carId", carInfo.getCarId());
        param.put("carInfoStatus", 1);
        RestResponse response = RestClient.requestForObject(BaseHttpClient.HttpMethod.POST, url, param, null, CarDynamicStockDTO.class);
        log.info("查询库存信息，carId={}，response={}", carInfo.getCarId(), JSON.toJSONString(response));
        return true;
    }
}
