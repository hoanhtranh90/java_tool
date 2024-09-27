package org.me.encode_decode;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


/**
 * @author sangnk
 * @Created 23/09/2024 - 8:47 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class RSA {
    public static String file_public = "/rsa_key/public_key.pem";
    public static String file_private = "/rsa_key/private_key.pem";

    public String  encrypt(String plainText) {
        try {
            PemObject pemObject = getPem(file_public);
            byte[] keyBytes = pemObject.getContent();
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    public String  decrypt(String encryptedText) {
        try {
            PemObject pemObject = getPem(file_private);
            byte[] keyBytes = pemObject.getContent();
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    private static PemObject getPem(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        PemReader pemReader = new PemReader(new InputStreamReader(resource.getInputStream()));
        return pemReader.readPemObject();
    }
}
