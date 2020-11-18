package com.jxzc.web.web;

import com.izu.framework.web.rest.response.RestResponse;
import com.jxzc.web.service.impl.DingTalkMsgPushServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static com.dingtalk.api.request.OapiRobotSendRequest.Links;

/**
 * @ClassPath com.jxzc.web.web.MsgServiceTest
 * @ClassName MsgServiceTest
 * @Description TODO
 * @Author whd
 * @Date 2020/11/17 16:52
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class MsgServiceTest {

    @Autowired
    DingTalkMsgPushServiceImpl msgPushService;

    @Test
    public void testTextSend(){
//        String makText = "#### 杭州天气 @18241891872\n" +
//                "9度，西北风1级，空气良89，相对温度73%\n\n" +
//                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
//                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n";
//        msgPushService.sendTextMsg("哈哈哈", "18241891872", false);

        ArrayList<Links> links = new ArrayList<Links>() {{
            add(new Links() {{
                setTitle("百度一下");
                setMessageURL("https://www.baidu.com");
                setPicURL("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2822468059,2673919372&fm=26&gp=0.jpg");
            }});
            add(new Links() {{
                setTitle("你就知道");
                setMessageURL("https://ayzy.online");
                setPicURL("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3588879057,4237930220&fm=26&gp=0.jpg");
            }});
        }};
        RestResponse response = msgPushService.sendFeedCardMsg(links);
    }
}
