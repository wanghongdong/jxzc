package com.jxzc.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 签名验签类
 */
public class SignatureUtils {

    private static final String RSA = "RSA";

    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    public static KeyPair generateRsaKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        keyPairGen.initialize(keySize, new SecureRandom());
        return keyPairGen.generateKeyPair();
    }

    public static PublicKey getRsaX509PublicKey(byte[] encodedKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static PrivateKey getRsaPkcs8PrivateKey(byte[] encodedKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static String sign(String algorithm, String privateKeyStr, String data, String charset) throws GeneralSecurityException {
        PrivateKey privateKey = SignatureUtils.getRsaPkcs8PrivateKey(Base64.decodeBase64(privateKeyStr));
        Signature signature = Signature.getInstance(algorithm);
        sign(algorithm, privateKey, data, charset);
        return Base64.encodeBase64String(signature.sign());
    }

    private static byte[] sign(String algorithm, PrivateKey privateKey, String data) throws GeneralSecurityException {
        return sign(algorithm, privateKey, data, DEFAULT_CHARSET);
    }

    private static byte[] sign(String algorithm, PrivateKey privateKey, String data, String charset) throws GeneralSecurityException {
        return sign(algorithm, privateKey, data.getBytes(Charset.forName(charset)));
    }

    private static byte[] sign(String algorithm, PrivateKey privateKey, String data, Charset charset) throws GeneralSecurityException {
        return sign(algorithm, privateKey, data.getBytes(charset));
    }

    private static byte[] sign(String algorithm, PrivateKey privateKey, byte[] data) throws GeneralSecurityException {
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        try {
            return sign(algorithm, privateKey, input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    private static byte[] sign(String algorithm,
                               PrivateKey privateKey, InputStream data) throws GeneralSecurityException, IOException {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        doUpdate(signature, data);
        return signature.sign();
    }

    private static boolean verify(String algorithm, PublicKey publicKey,
                                  String data, byte[] sign) throws GeneralSecurityException {
        return verify(algorithm, publicKey, data, DEFAULT_CHARSET, sign);
    }

    public static boolean verify(String algorithm, PublicKey publicKey,
                                 String data, String charset, byte[] sign) throws GeneralSecurityException {
        return verify(algorithm, publicKey, data.getBytes(Charset.forName(charset)), sign);
    }

    private static boolean verify(String algorithm, PublicKey publicKey,
                                  String data, Charset charset, byte[] sign) throws GeneralSecurityException {
        return verify(algorithm, publicKey, data.getBytes(charset), sign);
    }

    private static boolean verify(String algorithm,
                                  PublicKey publicKey, byte[] data, byte[] sign) throws GeneralSecurityException {
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        try {
            return verify(algorithm, publicKey, input, sign);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    private static boolean verify(String algorithm, PublicKey publicKey,
                                  InputStream input, byte[] sign) throws GeneralSecurityException, IOException {

        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        doUpdate(signature, input);
        return signature.verify(sign);
    }

    private static void doUpdate(Signature signature, InputStream input) throws IOException, SignatureException {
        byte[] buf = new byte[4096];
        int c;
        do {
            c = input.read(buf);
            if (c > 0) {
                signature.update(buf, 0, c);
            }
        } while (c != -1);
    }


    /**
     * 生成签名
     * @param privateKeyStr  私钥
     * @param data           需生成签名的字符串
     */
    public static String sign(String privateKeyStr, String data) throws GeneralSecurityException {
        PrivateKey privateKey = SignatureUtils.getRsaPkcs8PrivateKey(Base64.decodeBase64(privateKeyStr));
        byte[] sign = sign(Config.SHA1WithRSA, privateKey, data);
        return Base64.encodeBase64String(sign);
    }

    /**
     * 验证签名
     *
     * @param sign         签名
     * @param responseData 响应data
     */
    public static boolean verify(String sign, String responseData) {
        byte[] keyByte = Base64.decodeBase64(Config.THIRD_PUBLIC_KEY);
        byte[] signByte = Base64.decodeBase64(sign);
        try {
            PublicKey publicKey = SignatureUtils.getRsaX509PublicKey(keyByte);
            return SignatureUtils.verify(Config.SHA1WithRSA, publicKey, responseData, signByte);
        } catch (Exception e) {
            System.out.println("验证签名时异常" + JSON.toJSONString(e));
        }
        return false;
    }


    /**
     * 构建签名参数
     *
     */
    public static String buildSignParam(JSONObject json) {
        JSONObject result = removeEmptyVal(json);
        //去除空值后
        List<String> keys = new ArrayList<>(result.keySet());
        Map<String, Object> linkedMap = new LinkedHashMap<>();
        Collections.sort(keys);
        keys.forEach(key -> linkedMap.put(key, result.get(key)));
        return JSON.toJSONString(linkedMap);
    }


    /**
     * 移除空值
     *
     */
    public static JSONObject removeEmptyVal(JSONObject data) {
        List<String> keyList = new ArrayList<>();
        data.forEach((key, value) -> {
            if (value == null || StringUtils.isBlank(value.toString())) {
                keyList.add(key);
            }
        });
        keyList.forEach(data::remove);
        return data;
    }
}
