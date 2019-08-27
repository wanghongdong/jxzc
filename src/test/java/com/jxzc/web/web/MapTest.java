package com.jxzc.web.web;

import com.jxzc.web.utils.Encoding;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassPath com.jxzc.web.web.MapTest
 * @ClassName MapTest
 * @Description 测试map
 * @Author whd
 * @Date 2019/4/29 15:51
 * @Version 1.0
 */

public class MapTest {

    public static final Map<String, String> map = new ConcurrentHashMap<>();


    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "YwaUezy62cg-TbqbTHo2JqnmxLUCuNXN9E2WFRrUPI1fZ38amS3PCpkpXneCt9AZ_y3ewt71ENhteCMFYtQGjJgKH56R5Q_NDF5p0lfdMEDFzEEcFzgMME1CnibEPVuTCbUlOiCTIlNTQiBdOXk4TpnIVld7AOgnqurJJtMxvSMuybGW34A3FgLkgY4j6Kny097N5WaOL_T5RTHmAOnPtdrkDEc2IO1na54StF3KlpbTmw1XCbFRE-EM83dL607UQC9w7VAxejjRHFEapJi6zrJDsl8LVXWh1M09ReUTwk2PWmCcBV5BfVkMfgqcbecU3Slq155cQ36-IqrJCpa9Lcy4w5QDhZyjd8YuyLs_RKuGKvYOHSpWakybakosSUZOujaSmc8PMA14YodECZscKAcYNpnxGEftmfauWMs4piHM87bBxeavslU9GIrkwqBpEEJ_QUbEim6lzjj3Tvp3KdvlOz3_VPESQhfHYvOjhj2ryjnimW2_Z9Y89Ayh1iKPNvC2oPEedY2f5DYyxkcPCqeLyEPSWexxrCwFG7rKpCsPLbdVa8hWPGHnTAHl363fC_-FAM87WSxDfxuJ20Zjy-IKtm06CtLqEEfgluqjF0hKt76FqrT91cFzUgwye8tHZ3-8lOQLJIcR8-mbsBg7l_C3xsXNxKpJ9kGfEuqC7snyuantsouQIQjJnIv81CYRi3BU60iVYDqOi3O2MLl_9nUSvrTnBKgb6dAwQS0LTp5QF0bJQ0wlZJ6kz";
        Base64 base64 = new Base64();
        String newStr = new String(base64.decode(str));
        System.out.println("newStr: "+newStr);
        System.out.println("newStr encode : " + Encoding.getEncoding(newStr));
    }
}
