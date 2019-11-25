package com.jxzc.web.dao;

import com.jxzc.web.entity.CarInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInfoMapper {

    int insert(CarInfo record);

    int insertSelective(CarInfo record);
    /**
     *
     * @mbg.generated
     */
    CarInfo selectByPrimaryKey(Long carId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CarInfo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CarInfo record);

    CarInfo selectByVehicleLicense(String vehicleLicense);
}