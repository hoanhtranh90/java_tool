package org.me.word_pdf.msword;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.me.word_pdf.utils.MethodUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class WordUtils {

    public byte[] fromTemplate(String templateFile, Map<String, String> maps) throws IOException {
        Resource resource = null;
        InputStream fileIn = null;
        XWPFDocument document = null;
        try {
            resource = new ClassPathResource(templateFile);
            fileIn = resource.getInputStream();
            document = new XWPFDocument(fileIn);

            iterateThroughParagraphs(document, maps);
            iterateThroughTables(document, maps);
            iterateThroughFooters(document, maps);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            return null;
        } finally {
            resource = null;
            MethodUtils.closeRessource(fileIn);
            MethodUtils.closeRessource(document);
        }
    }

    public XWPFDocument fromTemplate(XWPFDocument document, Map<String, String> maps) throws IOException {
//        Path path = Paths.get(templateFile);
//        byte[] byteData = Files.readAllBytes(path);
//        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(byteData));
        iterateThroughParagraphs(document, maps);
        iterateThroughTables(document, maps);
        iterateThroughFooters(document, maps);
//        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
//        document.write(fileOutputStream);
//        document.close();
//        fileOutputStream.close();
        return document;
    }

    public void fromTemplate(String templateFile, String outputFile, Map<String, String> maps) throws IOException {
        Path path = Paths.get(templateFile);
        byte[] byteData = Files.readAllBytes(path);
        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(byteData));
        iterateThroughParagraphs(document, maps);
        iterateThroughTables(document, maps);
        iterateThroughFooters(document, maps);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        document.write(fileOutputStream);
        document.close();
        fileOutputStream.close();
    }

    public byte[] fromTemplate(String templateFile, String docxFile, String pdfFile, Map<String, String> maps, String systemFolder) throws IOException {
        Resource resource = null;
        InputStream fileIn = null;
        XWPFDocument document = null;
        try {
            resource = new ClassPathResource(templateFile);
            fileIn = resource.getInputStream();
            document = new XWPFDocument(fileIn);
            iterateThroughParagraphs(document, maps);
            iterateThroughTables(document, maps);
            iterateThroughFooters(document, maps);
            iterateThroughPictures(document, maps);

            //            //save docx to D:/file/
//            File file = new File("D:/file/temp.docx");
//            FileOutputStream fos = new FileOutputStream(file);
//            document.write(fos);
//            fos.close();

            FileOutputStream fileOutputStream = new FileOutputStream(docxFile);
            document.write(fileOutputStream);
            document.close();
            fileOutputStream.close();

//            //save docx to D:/file/
//            File file = new File("D:/file/temp.docx");
//            FileOutputStream fos = new FileOutputStream(file);
//            document.write(fos);
//            fos.close();



            /*
             * kiểm tra hệ thống có libreoffice4.2 không
             * Nếu có thì chạy lệnh "libreoffice7.6 --headless --convert-to pdf path_to_docx_file --outdir path_to_pdf_folder"
             * Nếu không có thì convert bằng docx4j
             */
            boolean isLibreOfficeInstalled = checkLibreOfficeVersion();
            if (isLibreOfficeInstalled) {
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
            } else {
                /* convert docx to pdf with doc4j */
                InputStream templateInputStream = new FileInputStream(docxFile);
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
                MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
                String outputfilepath = pdfFile;
                FileOutputStream os = new FileOutputStream(outputfilepath);
                Docx4J.toPDF(wordMLPackage, os);
                os.flush();
                os.close();
                /* end */
            }

            return Files.readAllBytes(Paths.get(pdfFile));


        } catch (Exception e) {
            return null;
        } finally {
            resource = null;
            MethodUtils.closeRessource(fileIn);
            MethodUtils.closeRessource(document);
        }
    }

    private boolean checkLibreOfficeVersion() {
        try {
            // Thực hiện lệnh kiểm tra phiên bản LibreOffice
            Process process = Runtime.getRuntime().exec("libreoffice7.6 --version");

            // Đọc kết quả từ lệnh kiểm tra
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("LibreOffice")) {
                    return true;
                }
            }

            // Đọc kết quả từ lệnh kiểm tra (trường hợp Windows)
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                if (line.contains("LibreOffice")) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * Add picture to docx file ( need create fake a picture in docx file first - name of file same name of picture in docx file)
     */
    private void iterateThroughPictures(XWPFDocument document, Map<String, String> maps) {
        try {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                if (entry.getKey().startsWith("image")) {
                    String linkImage = maps.get(entry.getKey());
                    URL url = new URL(linkImage);
                    InputStream is = url.openStream();
                    List<XWPFPictureData> allPictures = document.getAllPictures();
                    if (allPictures != null && allPictures.size() > 1) {
                        XWPFPictureData pictureData = allPictures.stream().filter(p -> p.getFileName().contains(entry.getKey())).findFirst().orElse(null);
                        String fileName = pictureData.getFileName();
                        System.out.println(fileName);
                        if (pictureData.getPictureType() == Document.PICTURE_TYPE_JPEG || pictureData.getPictureType() == Document.PICTURE_TYPE_PNG) {
                            byte[] bytes = IOUtils.toByteArray(is);
                            replacePictureData(pictureData, bytes);
                            System.out.println(fileName + " replaced image");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void replacePictureData(XWPFPictureData source, byte[] data) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(data);
             OutputStream out = source.getPackagePart().getOutputStream();
        ) {
            byte[] buffer = new byte[2048];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void iterateThroughParagraphs(XWPFDocument doc, Map<String, String> fieldsForReport) {
        for (XWPFParagraph paragraph : doc.getParagraphs()) {
            iterateThroughRuns(doc, paragraph, fieldsForReport);
        }
    }

    private void iterateThroughTables(XWPFDocument doc, Map<String, String> fieldsForReport) {
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        iterateThroughRuns(doc, paragraph, fieldsForReport);
                    }
                }
            }
        }
    }

    private void iterateThroughFooters(XWPFDocument doc, Map<String, String> fieldsForReport) {
        for (XWPFFooter footer : doc.getFooterList()) {
            for (XWPFParagraph paragraph : footer.getParagraphs()) {
                iterateThroughRuns(doc, paragraph, fieldsForReport);
            }
        }
    }

    private void iterateThroughRuns(XWPFDocument document, XWPFParagraph paragraph, Map<String, String> fieldsForReport) {
        List<XWPFRun> runs = paragraph.getRuns();

        if (runs != null) {
            int runsSize = runs.size();

            for (int index = 0; index < runsSize; index++) {
                XWPFRun currentRun = runs.get(index);
                String text = currentRun.getText(0);
//System.out.println("Text: " + text);
                if (text != null && text.contains("#")) {
                    int skipIndex = replaceText(document, paragraph, index, runs, fieldsForReport);
                    index += skipIndex;
                }
            }
        }
    }

    private int replaceText(XWPFDocument document, XWPFParagraph paragraph, int index, List<XWPFRun> runs, Map<String, String> maps) {
        XWPFRun currentRun = runs.get(index);
        String text = currentRun.getText(0);

        long count = text.chars().filter(ch -> ch == '#').count();
        long left = count;
        for (int numb = 0; numb < count; numb += 2) {
            left = count - numb;
            if (left > 1) {
                int sIdx = text.indexOf("#");
                int eIdx = text.indexOf("#", sIdx + 1);
                String replacedText = text.substring(sIdx, eIdx).replace("#", "").toLowerCase();

                if (!maps.containsKey(replacedText) || (maps.get(replacedText) == null)) continue;
                text = text.replace("#" + replacedText + "#", maps.get(replacedText));
            }
        }
        if (left == 1) {
            int idx = text.indexOf("#");
            String oriText = text.substring(0, idx);
            String endText = "";
            String replacedText = text.substring(idx).replace("#", "").toLowerCase();
            int lastIdx = index + 1;
            while (lastIdx < runs.size()) {
                XWPFRun nxtRun = runs.get(lastIdx);
                if (nxtRun.getText(0).contains("#")) {
                    int eIdx = nxtRun.getText(0).indexOf("#");
                    replacedText += nxtRun.getText(0).substring(0, eIdx).replace("#", "").toLowerCase();
                    endText = nxtRun.getText(0).substring(eIdx + 1);
//System.out.println("Replaced: " + replacedText);
                    if (!maps.containsKey(replacedText) || (maps.get(replacedText) == null)) return lastIdx - index;
                    break;
                }
                replacedText += nxtRun.getText(0);
                lastIdx++;
            }

//            currentRun.setText(oriText + maps.get(replacedText), 0);
//            for(int i = index + 1; i<=lastIdx; i++){
//                runs.get(i).setText("", 0);
//            }

            /// In the case of multiple lines
            String[] lines = maps.get(replacedText).split("\n");
            if (lines.length > 1) {
                for (int i = 0; i < lines.length; i++) {
                    // For every run except last one, add a carriage return.
                    String textForLine = lines[i];
                    if (i == lines.length - 1) {
                        currentRun.setText(textForLine, 0);
                        currentRun.addCarriageReturn();
                    } else {
                        paragraph.insertNewRun(index + i);
                        XWPFRun newRun = paragraph.getRuns().get(i);
                        CTRPr rPr = newRun.getCTR().isSetRPr() ? newRun.getCTR().getRPr() : newRun.getCTR().addNewRPr();
                        rPr.set(currentRun.getCTR().getRPr());
                        newRun.setText(textForLine);
                        newRun.addCarriageReturn(); //ADD THE NEW LINE
                    }
                }
                runs = paragraph.getRuns();
                for (int i = index + 1; i <= lastIdx; i++) {
                    runs.get(lines.length + i - 1).setText("", 0);
                }
            } else {
                currentRun.setText(oriText + maps.get(replacedText) + endText, 0);
                for (int i = index + 1; i <= lastIdx; i++) {
                    runs.get(i).setText("", 0);
                }
            }
            return lastIdx - index;
        }

//        currentRun.setText(text, 0);
        /// In the case of multiple lines
        String[] lines = text.split("\n");
        if (lines.length > 1) {
            for (int i = 0; i < lines.length; i++) {
                // For every run except last one, add a carriage return.
                String textForLine = lines[i];
                if (i == lines.length - 1) {
                    currentRun.setText(textForLine, 0);
                    currentRun.addCarriageReturn();
                } else {
                    paragraph.insertNewRun(index + i);
                    XWPFRun newRun = paragraph.getRuns().get(i);
                    CTRPr rPr = newRun.getCTR().isSetRPr() ? newRun.getCTR().getRPr() : newRun.getCTR().addNewRPr();
                    rPr.set(currentRun.getCTR().getRPr());
                    newRun.setText(textForLine);
                    newRun.addCarriageReturn(); //ADD THE NEW LINE
                }
            }
        } else {
            currentRun.setText(text, 0);
        }

        return 0;
    }

    public static String toRomanNumber(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }


}
