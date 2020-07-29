package com.jxzc.web.constant.violation;

/**
 * @ClassPath com.jxzc.web.constant.violation
 * @ClassName ViolationE
 * @Description TODO
 * @Author whd
 * @Date 2020/3/4 15:48
 * @Version 1.0
 */

public class ViolationEnum {

    public enum ServiceName{
        CXY(ViolationConstant.CXY_SERVICE, "车行易"),
        JSSJ(ViolationConstant.JS_SERVICE, "极速数据"),
        ;

        private String value;
        private String name;

        ServiceName(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

}
