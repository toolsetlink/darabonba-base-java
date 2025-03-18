// This file is auto-generated, don't edit it. Thanks.
package com.toolsetlink.darabonba.base;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Client {

    public static String timeRFC3339() throws Exception {
        // 使用当前时间并格式化为RFC3339格式
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }

    public static String generateNonce() throws Exception {
        // 生成8字节随机数据并转换为16位十六进制字符串
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        return sb.toString();
    }

    public static String generateSignature(String body, String nonce, String secretKey, String timestamp, String uri) throws Exception {
        // 拼接各部分并生成MD5签名
        List<String> parts = new ArrayList<>();
        if (body != null && !body.isEmpty()) {
            parts.add("body=" + body);
        }
        parts.add("nonce=" + nonce);
        parts.add("secretKey=" + secretKey);
        parts.add("timestamp=" + timestamp);
        parts.add("url=" + uri);

        String signStr = String.join("&", parts);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(signStr.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        return sb.toString();
    }
}