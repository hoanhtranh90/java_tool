package org.me.word_pdf.module;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author sangnk
 * @Created 18/09/2024 - 4:18 CH
 * @project = word_pdf
 * @_ Mô tả:
 */
public class WordToPDFLibreoffice {
    public void convertToPdfLibreoffice(String docxFile) {
        try {
            String systemFolder = "C:\\";
            String libreOfficeCommand = "libreoffice7.6 --headless --convert-to pdf " + docxFile + " --outdir " + systemFolder;
            Runtime.getRuntime().exec(libreOfficeCommand);
            Process process = Runtime.getRuntime().exec(libreOfficeCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println(exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test() {
        convertToPdfLibreoffice("template/file_test.docx");
    }
}
