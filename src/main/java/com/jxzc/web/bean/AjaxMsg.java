package com.jxzc.web.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * @ProjectName: jsjz
 * @PackageName: com.jxzc.web.bean
 * @ClassName: AjaxMsg
 * @Auther: wanghongdong
 * @Date: 2018/10/14 20 44
 * @Description: ajax返回状态bean
 * */
public class AjaxMsg implements Serializable {

    public static Integer SUCCESS_CODE = 1;

    public static Integer ERROR_CODE = 0;

    private Integer code = 1;

    private String msg;

    private Map<String,Object> map;

    public AjaxMsg(Integer code, String msg, Map<String, Object> map) {
        this.code = code;
        this.msg = msg;
        this.map = map;
    }

    public AjaxMsg(){}

    /**
     * @Author wanghongdong
     * @Description 返回成功，只有状态码
     * @Date  2018/10/14 20:53
     * @Param []
     * @return com.jxzc.web.bean.AjaxMsg
     **/
    public static AjaxMsg success(){
        return new AjaxMsg(SUCCESS_CODE,null,null);
    }

    public static AjaxMsg success(String msg){
        return new AjaxMsg(SUCCESS_CODE,msg,null);
    }

    public static AjaxMsg success(String msg, Map<String,Object> map){
        return new AjaxMsg(SUCCESS_CODE,msg,map);
    }

    public static AjaxMsg error(){
        return new AjaxMsg(ERROR_CODE,null,null);
    }

    public static AjaxMsg error(String msg){
        return new AjaxMsg(ERROR_CODE,msg,null);
    }

    public static AjaxMsg error(String msg, Map<String,Object> map){
        return new AjaxMsg(ERROR_CODE,msg,map);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
