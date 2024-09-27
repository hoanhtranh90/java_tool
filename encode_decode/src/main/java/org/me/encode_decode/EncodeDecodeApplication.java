package org.me.encode_decode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;

@SpringBootApplication
public class EncodeDecodeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EncodeDecodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        testAES();
        testRSA();
    }

    private void testAES() throws NoSuchAlgorithmException {
        AES aes = new AES();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        String data = "Hello World";
        byte[] encrypted = aes.encrypt(data.getBytes(), secretKey.getEncoded());
        System.out.println("Encrypted: " + new String(encrypted));
        byte[] decrypted = aes.decrypt(encrypted, secretKey.getEncoded());
        System.out.println("Decrypted: " + new String(decrypted));
    }

    private void testRSA() throws NoSuchAlgorithmException {
        RSA rsa = new RSA();
        String plainText = "Hello, RSA!";
        System.out.println("Original Text: " + plainText);

        String encryptedText = rsa.encrypt(plainText);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = rsa.decrypt(encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}
