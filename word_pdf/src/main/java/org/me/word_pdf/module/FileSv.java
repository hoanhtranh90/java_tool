package org.me.word_pdf.module;

import org.me.word_pdf.utils.MethodUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author sangnk
 * @Created 18/09/2024 - 4:48 CH
 * @project = word_pdf
 * @_ Mô tả:
 */
public class FileSv {
    public void saveFile(byte[] out, String fileName) {
        if (out != null) {
            File outputDir = null;
            File outputFile = null;
            FileOutputStream fos = null;
            try {
                outputDir = new File("output");
                if (!outputDir.exists()) {
                    outputDir.mkdirs();
                }

                outputFile = new File(outputDir, fileName);

                fos = new FileOutputStream(outputFile);
                fos.write(out);
                fos.close();

                System.out.println("File saved successfully at: " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                MethodUtils.closeRessource(fos);
            }
        } else {
            System.out.println("Failed to apply template.");
        }
    }
}
