package com.jxzc.web.web.jssj;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxzc.web.web.util.HttpUtil;

import java.net.URLEncoder;

/**
 * @ClassPath com.jxzc.web.web.jssj.Query
 * @ClassName Query
 * @Description TODO
 * @Author whd
 * @Date 2020/3/4 18:19
 * @Version 1.0
 */

public class Query {

    public static final String APPKEY = "79bb971630b4e850";// 你的appkey
    public static final String URL = "https://api.jisuapi.com/illegal/query";
    public static final String carorg = "";// 交管局代号
    public static final String lsprefix = "京";// 车牌前缀 utf8
    public static final String lsnum = "EBD825";// 车牌
    public static final String lstype = "02";// 车辆类型
    public static final String engineno = "173397901";// 发动机号
    public static final String frameno = "";// 车架号

    public static void main(String[] args) throws Exception {
        Get();
    }

    public static void Get() throws Exception {
        String result = null;
        String url = URL + "?appkey=" + APPKEY + "&carorg=" + carorg + "&lsprefix="
                + URLEncoder.encode(lsprefix, "utf-8") + "&lsnum=" + lsnum + "&lstype=" + lstype + "&frameno="
                + frameno;
        System.out.println(url);
        try {
            result = HttpUtil.sendGet(url, "utf-8");
            System.out.println(result);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getInteger("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = json.getJSONObject("result");
                if (resultarr != null) {
                    String lsprefix = resultarr.getString("lsprefix");
                    String lsnum = resultarr.getString("lsnum");
                    String carorg = resultarr.getString("carorg");
                    String usercarid = resultarr.getString("usercarid");
                    System.out.println(lsprefix + " " + lsnum + " " + carorg + " " + usercarid);
                    if (resultarr.get("list") != null) {
                        JSONArray list = resultarr.getJSONArray("list");
                        for (int j = 0; j < list.size(); j++) {
                            JSONObject list_obj = (JSONObject) list.get(j);
                            if (list_obj != null) {
                                String time = list_obj.getString("time");
                                String address = list_obj.getString("address");
                                String content = list_obj.getString("content");
                                String legalnum = list_obj.getString("legalnum");
                                String price = list_obj.getString("price");
                                String id = list_obj.getString("id");
                                String score = list_obj.getString("score");
                                System.out.println(time + " " + address + " " + content + " " + legalnum + " " + price
                                        + " " + id + " " + score);
                            }
                        }
                    }
                } else {
                    System.out.println("恭喜您，没有违章！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
