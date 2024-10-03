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

    }



}
