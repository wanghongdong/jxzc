package com.jxzc.web.service;

import com.izu.framework.web.rest.response.RestResponse;

import java.util.List;

/**
 * @ClassPath com.jxzc.web.service.MsgPushService
 * @ClassName MsgPushService
 * @Description TODO
 * @Author whd
 * @Date 2020/11/17 11:31
 * @Version 1.0
 */

public interface MsgPushService {

    RestResponse sendTextMsg(String content, List<String> atMobiles, boolean atAll);

    RestResponse sendTextMsg(String content, String phone, boolean atAll);

}
