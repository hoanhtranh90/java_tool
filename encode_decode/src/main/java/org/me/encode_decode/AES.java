package org.me.encode_decode;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class AES {

    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data);

            return encryptedBytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("encrypt fail!", e);
        }
    }


    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            String encryptedText = Base64.getEncoder().encodeToString(data);
            System.out.println("Encrypted Base64: " + encryptedText);
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return decryptedBytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("decrypt fail!", e);
        }
    }


}