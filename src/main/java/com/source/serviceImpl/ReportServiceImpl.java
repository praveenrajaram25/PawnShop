package com.source.serviceImpl;

import com.source.dao.CustomerDataRepository;
import com.source.model.CustomerData;
import com.source.service.ReportService;
import com.source.utils.PawnShopUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    CustomerDataRepository customerRepo;

    @Autowired
    PawnShopUtils utils;

    @Override
    public ResponseEntity<Resource> generateDailyReport(Date sqlDate) {
        ResponseEntity<Resource> res = null;
        List<CustomerData> lendData = customerRepo.findByRequestedDate(sqlDate);
        List<CustomerData> returnData = customerRepo.findByReturnedDate(sqlDate);

        ArrayList<String> lendReportData = new ArrayList<String>();
        lendReportData.add(utils.getReportHeader());
        lendData.forEach(item -> lendReportData.add(item.toReport()));

        ArrayList<String> returnReportData = new ArrayList<String>();
        returnReportData.add(utils.getReportHeader());
        returnData.forEach(item -> returnReportData.add(item.toReport()));

        String filePath = utils.getDailyReportPath()+"DailyReport.xlsx";


        boolean excelFile=writeToExcel(filePath,"DailyReport.xlsx", lendReportData, returnReportData,"daily");

        if(excelFile){
            res = utils.downloadExcel(filePath,"DailyReport.xlsx");
        }

        return res;
    }

    @Override
    public ResponseEntity<Resource> generateTimeBasedReport(String StartDate, String endDate) {
        ResponseEntity<Resource> res = null;

        Date stDt=utils.convertStringToDate(StartDate);
        Date edDt=utils.convertStringToDate(endDate);
        List<CustomerData> lendData = customerRepo.findByRequestedDateBetween(stDt,edDt);

        List<CustomerData> returnData = customerRepo.findByReturnedDateBetween(stDt,edDt);

        ArrayList<String> lendReportData = new ArrayList<String>();
        lendReportData.add(utils.getReportHeader());
        lendData.forEach(item -> lendReportData.add(item.toReport()));

        ArrayList<String> returnReportData = new ArrayList<String>();
        returnReportData.add(utils.getReportHeader());
        returnData.forEach(item -> returnReportData.add(item.toReport()));

        String filePath = utils.getTimeBasedReportPath()+"TimeBasedReport.xlsx";


        boolean excelFile=writeToExcel(filePath,"TimeBasedReport.xlsx", lendReportData, returnReportData,"time");

        if(excelFile){
            res = utils.downloadExcel(filePath,"TimeBasedReport.xlsx");
        }

        return res;
    }

    @Override
    public ResponseEntity<Resource> generateCustomReport(String name, String place, String requestedDate, String returnedDate, String materialNature, String materialType, String parameterType) {

        ResponseEntity<Resource> res = null;
        String parameter="";
        List<CustomerData> parameterBasedList=null;
        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(utils.getReportHeader());
        if(StringUtils.equalsIgnoreCase(parameterType,"name")){
            parameter="NameBasedData_"+name;
            parameterBasedList=customerRepo.findByName(name);
            parameterBasedList.forEach(item->dataList.add(item.toReport()));
        }else if(StringUtils.equalsIgnoreCase(parameterType,"place")){
            parameter="PlaceBasedData_"+place;
            parameterBasedList=customerRepo.findByPlace(place);
            parameterBasedList.forEach(item->dataList.add(item.toReport()));
        }else if(StringUtils.equalsIgnoreCase(parameterType,"materialNature")){
            parameter="MaterialNatureBasedData_"+materialNature;
            parameterBasedList=customerRepo.findByMaterialNature(materialNature);
            parameterBasedList.forEach(item->dataList.add(item.toReport()));
        }else if(StringUtils.equalsIgnoreCase(parameterType,"materialType")){
            parameter="MaterialTypeBasedData_"+materialType;
            parameterBasedList=customerRepo.findByMaterialType(materialType);
            parameterBasedList.forEach(item->dataList.add(item.toReport()));
        }else if(StringUtils.equalsIgnoreCase(parameterType,"requestedDate")){
            Date reqDt=utils.convertStringToDate(requestedDate);
            parameter="RequestedDateBasedData_"+requestedDate;
            parameterBasedList=customerRepo.findByRequestedDate(reqDt);
            parameterBasedList.forEach(item->dataList.add(item.toReport()));
        }else if(StringUtils.equalsIgnoreCase(parameterType,"returnedDate")){
            Date retDt=utils.convertStringToDate(returnedDate);
            parameter="ReturnedDateBasedData_"+returnedDate;
            parameterBasedList=customerRepo.findByReturnedDate(retDt);
            parameterBasedList.forEach(item->dataList.add(item.toReport()));
        }

        String fileName=parameter+".xlsx";
        String filePath = utils.getCustomReportPath()+fileName;

        boolean excelFile=writeToExcel(filePath,fileName, dataList, null,"custom");

        if(excelFile){
            res = utils.downloadExcel(filePath,fileName);
        }

        return res;

    }


    public  boolean writeToExcel (String filepath,String fileName,ArrayList<String> lendReportData,ArrayList<String> returnReportData,String reportType){

        boolean report=false;
        try (Workbook workbook = new XSSFWorkbook()) {

            if(StringUtils.equalsIgnoreCase(reportType,"daily") || StringUtils.equalsIgnoreCase(reportType,"time")){
                writeToSheet(workbook, lendReportData, "LendData");
                writeToSheet(workbook, returnReportData, "ReturnData");
            }else{
                writeToSheet(workbook, lendReportData, "CustomerData");
            }


            try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
                workbook.write(fileOut);
                logger.info(
                        "Excel file '" + fileName + "' created successfully.");
                report=true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return report;
    }

    private static void writeToSheet(Workbook workbook, List<String> dataList, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);
        int rowNum = 0;

        for (String data : dataList) {
            Row row = sheet.createRow(rowNum++);
            String[] values = data.split("\\|");

            for (int i = 0; i < values.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values[i]);
            }
        }
    }
}
