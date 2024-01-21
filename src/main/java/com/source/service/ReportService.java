package com.source.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

public interface ReportService {


    ResponseEntity<Resource> generateDailyReport(Date sqlDate);

    ResponseEntity<Resource> generateTimeBasedReport(String StartDate, String endDate);

    ResponseEntity<Resource> generateCustomReport(String name, String place, String requestedDate, String returnedDate, String materialNature, String materialType, String parameterType);
}
