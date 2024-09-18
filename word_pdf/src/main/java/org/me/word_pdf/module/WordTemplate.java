package org.me.word_pdf.module;

import org.me.word_pdf.msword.WordUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordTemplate {
    public byte[] applyTemplate(String template, Map<String, String> maps) {
        try {
            WordUtils wordUtils = new WordUtils();
            return wordUtils.fromTemplate("template/"+template, maps);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void test() {
        WordTemplate wordTemplate = new WordTemplate();
        Map<String, String> maps = new HashMap<>();
        maps.put("current_day", "18");
        maps.put("current_month", "09");
        maps.put("current_year", "2024");
        maps.put("field1", "h1");
        maps.put("field2", "h2");
        maps.put("field3", "h3");
        byte[] out = wordTemplate.applyTemplate("file_test.docx", maps);
        FileSv fileSv = new FileSv();
        fileSv.saveFile(out, "file_test.docx");
        System.out.println("done");
    }

}
