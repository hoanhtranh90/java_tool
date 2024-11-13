package org.me.encode_decode;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author sangnk
 * @Created 11/10/2024 - 1:43 CH
 * @project = java_tool
 * @_ Mô tả:
 */
public class base64Test {
    public static void main(String[] args) {
        base64Test base64Test = new base64Test();
        System.out.println(base64Test.createSecretKey());
    }
    public String createSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[36]; //36*8=288 (>256 bits required for HS256)
        secureRandom.nextBytes(secretBytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(secretBytes);
    }
}
