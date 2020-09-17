package com.jxzc.web.entity;

import java.util.Date;

public class CarInfo {
    private Long carId;

    private String vehicleLicense;

    private String engineNum;

    private String vehicleVin;

    private String vehicleColor;

    private String vehicleLicenseColor;

    private String vehicleTypeCode;

    private String vehicleTypeName;

    private String manufacturerName;

    private Integer modelId;

    private String vehicleModelCode;

    private String vehicleModelName;

    private String vehicleType;

    private Integer cityId;

    private String cityCode;

    private String cityName;

    private String belongBussCode;

    private String operateBussCode;

    private Integer belongStoreId;

    private String belongStoreCode;

    private Integer adminCityId;

    private Integer adminStoreId;

    private String adminStoreCode;

    private Integer locationCityId;

    private Integer locationStoreId;

    private String locationStoreCode;

    private Byte vehicleStatus;

    private Byte operateStatus;

    private Byte businessType;

    private String commercialCompany;

    private String commercialPolicy;

    private String trafficCompany;

    private String trafficPolicy;

    private String licensePicture;

    private Integer vehicleKilometers;

    private Integer maintenanceKilometers;

    private String inspectionDate;

    private String trafficPolicyTime;

    private String commercialPolicyTime;

    private Boolean inspectionStatus;

    private Boolean policyStatus;

    private Boolean putaway;

    private Boolean valid;

    private Integer creterId;

    private String creterName;

    private Integer updateId;

    private String updateName;

    private Date createDate;

    private Date updateDate;

    private Date onlineTime;

    private Date lastCheckCarTime;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense == null ? null : vehicleLicense.trim();
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum == null ? null : engineNum.trim();
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin == null ? null : vehicleVin.trim();
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor == null ? null : vehicleColor.trim();
    }

    public String getVehicleLicenseColor() {
        return vehicleLicenseColor;
    }

    public void setVehicleLicenseColor(String vehicleLicenseColor) {
        this.vehicleLicenseColor = vehicleLicenseColor == null ? null : vehicleLicenseColor.trim();
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode == null ? null : vehicleTypeCode.trim();
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName == null ? null : vehicleTypeName.trim();
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName == null ? null : manufacturerName.trim();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getVehicleModelCode() {
        return vehicleModelCode;
    }

    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode == null ? null : vehicleModelCode.trim();
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName == null ? null : vehicleModelName.trim();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getBelongBussCode() {
        return belongBussCode;
    }

    public void setBelongBussCode(String belongBussCode) {
        this.belongBussCode = belongBussCode == null ? null : belongBussCode.trim();
    }

    public String getOperateBussCode() {
        return operateBussCode;
    }

    public void setOperateBussCode(String operateBussCode) {
        this.operateBussCode = operateBussCode == null ? null : operateBussCode.trim();
    }

    public Integer getBelongStoreId() {
        return belongStoreId;
    }

    public void setBelongStoreId(Integer belongStoreId) {
        this.belongStoreId = belongStoreId;
    }

    public String getBelongStoreCode() {
        return belongStoreCode;
    }

    public void setBelongStoreCode(String belongStoreCode) {
        this.belongStoreCode = belongStoreCode == null ? null : belongStoreCode.trim();
    }

    public Integer getAdminCityId() {
        return adminCityId;
    }

    public void setAdminCityId(Integer adminCityId) {
        this.adminCityId = adminCityId;
    }

    public Integer getAdminStoreId() {
        return adminStoreId;
    }

    public void setAdminStoreId(Integer adminStoreId) {
        this.adminStoreId = adminStoreId;
    }

    public String getAdminStoreCode() {
        return adminStoreCode;
    }

    public void setAdminStoreCode(String adminStoreCode) {
        this.adminStoreCode = adminStoreCode == null ? null : adminStoreCode.trim();
    }

    public Integer getLocationCityId() {
        return locationCityId;
    }

    public void setLocationCityId(Integer locationCityId) {
        this.locationCityId = locationCityId;
    }

    public Integer getLocationStoreId() {
        return locationStoreId;
    }

    public void setLocationStoreId(Integer locationStoreId) {
        this.locationStoreId = locationStoreId;
    }

    public String getLocationStoreCode() {
        return locationStoreCode;
    }

    public void setLocationStoreCode(String locationStoreCode) {
        this.locationStoreCode = locationStoreCode == null ? null : locationStoreCode.trim();
    }

    public Byte getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Byte vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Byte getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Byte operateStatus) {
        this.operateStatus = operateStatus;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public String getCommercialCompany() {
        return commercialCompany;
    }

    public void setCommercialCompany(String commercialCompany) {
        this.commercialCompany = commercialCompany == null ? null : commercialCompany.trim();
    }

    public String getCommercialPolicy() {
        return commercialPolicy;
    }

    public void setCommercialPolicy(String commercialPolicy) {
        this.commercialPolicy = commercialPolicy == null ? null : commercialPolicy.trim();
    }

    public String getTrafficCompany() {
        return trafficCompany;
    }

    public void setTrafficCompany(String trafficCompany) {
        this.trafficCompany = trafficCompany == null ? null : trafficCompany.trim();
    }

    public String getTrafficPolicy() {
        return trafficPolicy;
    }

    public void setTrafficPolicy(String trafficPolicy) {
        this.trafficPolicy = trafficPolicy == null ? null : trafficPolicy.trim();
    }

    public String getLicensePicture() {
        return licensePicture;
    }

    public void setLicensePicture(String licensePicture) {
        this.licensePicture = licensePicture == null ? null : licensePicture.trim();
    }

    public Integer getVehicleKilometers() {
        return vehicleKilometers;
    }

    public void setVehicleKilometers(Integer vehicleKilometers) {
        this.vehicleKilometers = vehicleKilometers;
    }

    public Integer getMaintenanceKilometers() {
        return maintenanceKilometers;
    }

    public void setMaintenanceKilometers(Integer maintenanceKilometers) {
        this.maintenanceKilometers = maintenanceKilometers;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate == null ? null : inspectionDate.trim();
    }

    public String getTrafficPolicyTime() {
        return trafficPolicyTime;
    }

    public void setTrafficPolicyTime(String trafficPolicyTime) {
        this.trafficPolicyTime = trafficPolicyTime == null ? null : trafficPolicyTime.trim();
    }

    public String getCommercialPolicyTime() {
        return commercialPolicyTime;
    }

    public void setCommercialPolicyTime(String commercialPolicyTime) {
        this.commercialPolicyTime = commercialPolicyTime == null ? null : commercialPolicyTime.trim();
    }

    public Boolean getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Boolean inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public Boolean getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(Boolean policyStatus) {
        this.policyStatus = policyStatus;
    }

    public Boolean getPutaway() {
        return putaway;
    }

    public void setPutaway(Boolean putaway) {
        this.putaway = putaway;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Integer getCreterId() {
        return creterId;
    }

    public void setCreterId(Integer creterId) {
        this.creterId = creterId;
    }

    public String getCreterName() {
        return creterName;
    }

    public void setCreterName(String creterName) {
        this.creterName = creterName == null ? null : creterName.trim();
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getLastCheckCarTime() {
        return lastCheckCarTime;
    }

    public void setLastCheckCarTime(Date lastCheckCarTime) {
        this.lastCheckCarTime = lastCheckCarTime;
    }
}