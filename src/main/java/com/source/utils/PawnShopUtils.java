package com.source.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Service
public class PawnShopUtils {

    @Value("${pdf.file.path}")
    private String pdfFilePath;

    @Value("${dailyreport.file.path}")
    private String dailyReportPath;

    @Value("${timebasedreport.file.path}")
    private String timeBasedReportPath;

    @Value("${customreport.file.path}")
    private String customReportPath;

    @Value("${reportHeader}")
    private String reportHeader;

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public String getDailyReportPath() {
        return dailyReportPath;
    }

    public String getCustomReportPath() {
        return customReportPath;
    }

    public String getTimeBasedReportPath() {
        return timeBasedReportPath;
    }

    public String getReportHeader(){return reportHeader;}



    /*public ResponseEntity<byte[]> downloadPDF(String filePath) throws IOException {
        // Provide the absolute path to your PDF file


        File file = new File(filePath);
        byte[] filesystem = Files.readAllBytes(Paths.get(filePath));
        //InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
       // byte[] filesystem = resource.getDescription().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "file.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(filesystem);
    }*/

    private final ResourceLoader resourceLoader;

    public PawnShopUtils(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResponseEntity<Resource> downloadPDF(String filePath,String fileName) throws IOException {


        Resource resource = resourceLoader.getResource("file:" + filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    public java.sql.Date convertStringToDate(String date) {
        java.sql.Date sqlDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(date);
            sqlDate = new java.sql.Date(utilDate.getTime());


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    public ResponseEntity<Resource> downloadExcel(String filePath, String fileName) {
        Resource resource = resourceLoader.getResource("file:" + filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}