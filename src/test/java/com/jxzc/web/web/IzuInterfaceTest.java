package com.jxzc.web.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.izu.framework.web.rest.client.RestClient;
import com.izu.framework.web.rest.response.RestResponse;
import com.jxzc.web.utils.Config;
import com.jxzc.web.utils.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.*;

import static com.izu.framework.web.rest.client.BaseHttpClient.HttpMethod.POST;

/**
 * @ClassPath com.jxzc.web.web.IzuInterfaceTest
 * @ClassName IzuInterfaceTest
 * @Description TODO
 * @Author whd
 * @Date 2020/9/19 14:17
 * @Version 1.0
 */
@Slf4j
public class IzuInterfaceTest {


    public static void main(String[] args) {
        String url = "https://pre-open-web.izuche.com/api/channel/asset/getStoreInfoList.json";
        Map<String, Object> httpParams = new HashMap<>();
        httpParams.put("isPaging", 0);
        httpParams.put("appId", "10000");
        httpParams.put("timestamp", System.currentTimeMillis());
        getSign(httpParams);
        RestResponse restResponse = RestClient.requestForList(POST, url, httpParams, null, JSONObject.class);
        log.info("params={}, /n, response={}", httpParams.toString(), JSONObject.toJSONString(restResponse));
    }

    private static void getSign(Map<String, Object> httpParams) {
        try {
            String s = buildSignParam(httpParams);
            String sign = SignatureUtils.sign(Config.THIRD_PRIVATE_KEY, s);
            httpParams.put("sign", sign);
        } catch (Exception e) {
            System.out.println(JSON.toJSONString(e));
        }
    }

    private static String buildSignParam(Map<String, Object> map) {
        Map<String, String> linkedMap = new LinkedHashMap<>();
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        keys.forEach(key -> {
            Object val = map.get(key);
            if (val == null) {
                return;
            }
            if (StringUtils.isBlank(val.toString())) {
                return;
            }
            linkedMap.put(key, val.toString());
        });
        return JSON.toJSONString(linkedMap);
    }

}
