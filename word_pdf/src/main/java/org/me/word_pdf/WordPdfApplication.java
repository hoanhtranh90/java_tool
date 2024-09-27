package org.me.word_pdf;

import org.me.word_pdf.module.AddWaterMark;
import org.me.word_pdf.module.WordTemplate;
import org.me.word_pdf.module.WordToPdfDoc4j;
import org.me.word_pdf.utils.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WordPdfApplication  implements CommandLineRunner {
    @Autowired
    private WordTemplate wordTemplate;
    @Autowired
    private WordToPdfDoc4j wordToPdfDoc4j;
    @Autowired
    private AddWaterMark addWaterMark;

    @Autowired
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(WordPdfApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        wordTemplate.test();
//        wordToPdfDoc4j.test();
        addWaterMark.test();
        SpringApplication.exit(context, () -> 0);
    }


}
