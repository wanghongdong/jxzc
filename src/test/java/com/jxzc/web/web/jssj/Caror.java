package com.jxzc.web.web.jssj;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxzc.web.web.util.HttpUtil;

/**
 * @ClassPath com.jxzc.web.web.Caror
 * @ClassName Caror
 * @Description TODO
 * @Author whd
 * @Date 2020/3/4 18:08
 * @Version 1.0
 */

public class Caror {

    public static final String APPKEY = "79bb971630b4e850";// 你的appkey
    public static final String URL = "https://api.jisuapi.com/illegal/carorg";

    public static void main(String[] args) {
        Get();
    }

    public static void Get() {
        String result = null;
        String url = URL + "?appkey=" + APPKEY;

        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getInteger("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = json.getJSONObject("result");
                JSONArray data = resultarr.getJSONArray("data");
                for (int i = 0; i < data.size(); i++) {
                    JSONObject obj = (JSONObject) data.get(i);
                    String province = obj.getString("province");
                    String lsprefix = obj.getString("lsprefix");
                    String lsnum = obj.getString("lsnum");
                    String carorg = obj.getString("carorg");
                    String frameno = obj.getString("frameno");
                    String engineno = obj.getString("engineno");
                    System.out.println(
                            province + " " + lsprefix + " " + lsnum + " " + carorg + " " + frameno + " " + engineno);
                    if (obj.getJSONArray("list") != null) {
                        JSONArray list = obj.getJSONArray("list");
                        for (int j = 0; j < list.size(); j++) {
                            JSONObject list_obj = (JSONObject) list.get(j);
                            String city = list_obj.getString("city");
                            String lsprefix1 = list_obj.getString("lsprefix");
                            String lsnum1 = list_obj.getString("lsnum");
                            String carorg1 = "";
                            if (list_obj.get("carorg") != null) {
                                carorg1 = list_obj.getString("carorg");
                            }
                            String frameno1 = "";
                            if (list_obj.get("frameno") != null) {
                                frameno1 = list_obj.getString("frameno");
                            }
                            String engineno1 = "";
                            if (list_obj.get("engineno") != null) {
                                engineno1 = list_obj.getString("engineno");
                            }
                            System.out.println(city + " " + lsprefix1 + " " + lsnum1 + " " + carorg1 + " " + frameno1
                                    + " " + engineno1);
                        }
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
