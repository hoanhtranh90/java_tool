package org.me.encode_decode;

import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
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
import java.security.NoSuchAlgorithmException;
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
    public static String PKCS8_file_private = "/rsa_key/PKCS8_private_key.pem";
    public static String PKCS8_file_public = "/rsa_key/PKCS8_public_key.pem";

    public String encrypt(String plainText) {
        try {
            PemObject pemObject = getPem(file_public, 1);
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

    /*
     * PKCS#1 format (which starts with -----BEGIN RSA PRIVATE KEY-----),
     * PKCS#8 format (which starts with -----BEGIN PRIVATE KEY-----)
     * if the key is in PKCS#1 format, we need to convert it to PKCS#8 format
     */
    public String decrypt(String encryptedText) {
        try {
            PemObject pemObject = getPem(file_private , 2);
            byte[] keyBytes = pemObject.getContent();
            System.out.println(pemObject.getType());
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

    private byte[] convertPkcs1ToPkcs8(byte[] pkcs1Bytes) throws Exception {
        // Use BouncyCastle library for conversion
        RSAPrivateKey rsaPrivateKey = RSAPrivateKey.getInstance(pkcs1Bytes);
        PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), rsaPrivateKey);
        return privateKeyInfo.getEncoded();
    }

    private static PemObject getPem(String filename, Integer type) throws IOException {
        Resource resource = new ClassPathResource(filename);
        try (PEMParser pemParser = new PEMParser(new InputStreamReader(resource.getInputStream()))) {
            try {
                Object object = pemParser.readObject();
                if (object instanceof PEMKeyPair) {
                    System.out.println("The key is in PKCS#1 format.");
                    PEMKeyPair privateKeyInfo = (PEMKeyPair) object;
                    return new PemObject("RSA PRIVATE KEY", privateKeyInfo.getPrivateKeyInfo().getEncoded());
                }
            } catch (Exception e) {
                System.out.println("The key is in PKCS#8 format.");
            }
        }
        try (PemReader pemReader = new PemReader(new InputStreamReader(resource.getInputStream()))) {
            return pemReader.readPemObject();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        testRSA();
    }

    public static void testRSA() throws NoSuchAlgorithmException {
        RSA rsa = new RSA();
        String plainText = "Hello, RSA!";
        System.out.println("Original Text: " + plainText);

        String encryptedText = rsa.encrypt(plainText);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = rsa.decrypt(encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}
