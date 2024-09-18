package org.me.word_pdf.module;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.me.word_pdf.utils.MethodUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author sangnk
 * @Created 18/09/2024 - 4:18 CH
 * @project = word_pdf
 * @_ Mô tả:
 */
@Service
public class WordToPdfDoc4j {

    public byte[] convertToPdfDoc4j(String docxFile) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        Resource resource = null;
        InputStream templateInputStream = null;
        try {
            resource = new ClassPathResource(docxFile);
            templateInputStream = resource.getInputStream();

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
            byteArrayOutputStream = new ByteArrayOutputStream();
            Docx4J.toPDF(wordMLPackage,byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MethodUtils.closeRessource(byteArrayOutputStream);
            MethodUtils.closeRessource(templateInputStream);
        }
        return null;
    }

    public void test() {
        try {
            byte[] out = convertToPdfDoc4j("template/file_test.docx");
            FileSv fileSv = new FileSv();
            fileSv.saveFile(out, "file_test.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
