package org.me.word_pdf.module;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.wml.R;
import org.me.word_pdf.utils.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author sangnk
 * @Created 19/09/2024 - 2:12 CH
 * @project = java_tool
 * @_ Mô tả:
 */
@Service
public class AddWaterMark {
    public static final String DEST = "output_watermark.pdf";

    public void addWaterMark(InputStream inputStream, String waterMarkText, OutputStream outputStream) {

        PdfReader reader = null;
        PdfStamper stamper = null;

        try {

            reader = new PdfReader(inputStream);
            stamper = new PdfStamper(reader, outputStream);
//            stamper = new PdfStamper(reader, outputStream);

            BaseFont baseFont = BaseFont.createFont(getFont("fonts/vuTimes.ttf"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.ITALIC, BaseColor.GRAY);
            Phrase phrase = new Phrase(waterMarkText, font);

            PdfContentByte over;

            int n = reader.getNumberOfPages();

            for (int i = 1; i <= n; i++) {

                // get page size and position
                over = stamper.getOverContent(i);
                over.saveState();

                // set transparency
                PdfGState state = new PdfGState();
                state.setFillOpacity(0.5f);
                over.setGState(state);

                // add watermark text
                ColumnText.showTextAligned(over,
                        Element.ALIGN_CENTER, //Keep waterMark center aligned
                        phrase, 300f, 500f, 45f); // 45f is the rotation angle

                over.restoreState();
            }
        } catch (Exception ex) {
            throw new RuntimeException("StorageException.convertToPdf", ex);
        } finally {
            try {
                stamper.close();
            } catch (Exception ex) {
            }
            try {
                reader.close();
            } catch (Exception ex) {
            }
        }
    }

    public String getFont(String path) {
        String baseFontPath = "C:/project/java_tool/word_pdf/src/main/resources";
        return baseFontPath + "/" + path;
    }

    public void test(){
        System.out.println("Test watermark run");
        String templateFile = "template/file_test.pdf";
        Resource resource = null;
        InputStream fileIn = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            resource = new ClassPathResource(templateFile);
            fileIn = resource.getInputStream();
            addWaterMark(fileIn, "Watermark", byteArrayOutputStream);
            FileSv fileSv = new FileSv();
            fileSv.saveFile(byteArrayOutputStream.toByteArray(), "file_test 1.pdf");

        }
        catch (Exception ex) {
            throw new RuntimeException("StorageException.convertToPdf", ex);
        } finally {
            MethodUtils.closeRessource(fileIn);
            MethodUtils.closeRessource(byteArrayOutputStream);
        }
    }
}
