package com.jxzc.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.izu.framework.web.rest.response.RestResponse;
import com.jxzc.web.service.MsgPushService;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.dingtalk.api.request.OapiRobotSendRequest.*;

/**
 * @ClassPath com.jxzc.web.service.impl.MsgPushServiceImpl
 * @ClassName MsgPushServiceImpl
 * @Description TODO
 * @Author whd
 * @Date 2020/11/17 11:31
 * @Version 1.0
 */
@Slf4j
@Service
public class DingTalkMsgPushServiceImpl implements MsgPushService {

    @Value("${ding.talk.warn.url}")
    private String dingTalkWarnUrl;

    @Value("${ding.talk.warn.token}")
    private String dingTalkWarnToken;

    @Override
    public RestResponse sendTextMsg(String content, String phone, boolean atAll) {
        return this.sendTextMsg(content, new ArrayList<String>(){{add(phone);}}, atAll);
    }

    @Override
    public RestResponse sendTextMsg(String content, List<String> atMobiles, boolean atAll){
        DingTalkClient client = new DefaultDingTalkClient(this.getUrl());
        OapiRobotSendRequest textRequest = new OapiRobotSendRequest();
        textRequest.setMsgtype("text");
        textRequest.setText(new Text(){{
            setContent(content);
        }});
        textRequest.setAt(new At(){{
            setAtMobiles(atMobiles);
            setIsAtAll(atAll);
        }});
        try {
            OapiRobotSendResponse response = client.execute(textRequest);
            log.info("request：{}，response；{}", JSON.toJSONString(textRequest), JSON.toJSONString(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return RestResponse.success(null);
    }

    public RestResponse sendLinkMsg(String msgUrl, String picUrl, String title, String text){
        DingTalkClient client = new DefaultDingTalkClient(this.getUrl());
        OapiRobotSendRequest linkRequest = new OapiRobotSendRequest();
        linkRequest.setMsgtype("link");
        linkRequest.setLink(new Link(){{
            setMessageUrl(msgUrl);
            setPicUrl(picUrl);
            setTitle(title);
            setText(text);
        }});
        try {
            OapiRobotSendResponse response = client.execute(linkRequest);
            log.info("request：{}，response；{}", JSON.toJSONString(linkRequest), JSON.toJSONString(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return RestResponse.success(null);
    }

    public RestResponse sendMarkdownMsg(String title, String text){
        DingTalkClient client = new DefaultDingTalkClient(this.getUrl());
        OapiRobotSendRequest markDownRequest = new OapiRobotSendRequest();
        markDownRequest.setMsgtype("markdown");
        markDownRequest.setMarkdown(new Markdown(){{
            setTitle(title);
            setText(text);
        }});
        try {
            OapiRobotSendResponse response = client.execute(markDownRequest);
            log.info("request：{}，response；{}", JSON.toJSONString(markDownRequest), JSON.toJSONString(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return RestResponse.success(null);
    }

    public RestResponse sendFeedCardMsg(List<Links> links){
        DingTalkClient client = new DefaultDingTalkClient(this.getUrl());
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("feedCard");
        request.setFeedCard(new Feedcard(){{
            setLinks(links);
        }});
        try {
            OapiRobotSendResponse response = client.execute(request);
            log.info("request：{}，response；{}", JSON.toJSONString(request), JSON.toJSONString(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return RestResponse.success(null);
    }

    private String getUrl(){
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + dingTalkWarnToken;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(dingTalkWarnToken.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            return dingTalkWarnUrl + String.format("&timestamp=%s&sign=%s", timestamp, sign);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return dingTalkWarnUrl;
    }
}
