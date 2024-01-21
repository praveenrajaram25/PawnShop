package com.source.controller;

import com.source.model.CustomerData;
import com.source.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
public class ShopDataController {

    private static final Logger logger = LoggerFactory.getLogger(ShopDataController.class);


    @Autowired
    LendService lendService;

    @Autowired
    ReturnService returnService;

    @Autowired
    GlobalSearchService globalSearchService;

    @Autowired
    ReportService reportService;



    @PostMapping("/pawnshop/insertRecord")
    public ResponseEntity<Resource> generateReceipt(@RequestBody Map<String, String> customerData) {

        ResponseEntity<Resource> res = lendService.processRecord(customerData);
        return res;
    }


    @PostMapping("/pawnshop/fetchRecord")
    @ResponseBody
    public CustomerData fetchRecord(@RequestParam String trackId ) {
        if(StringUtils.isNotBlank(trackId)){
            int trackingId = Integer.parseInt(trackId);
            CustomerData customerRecord = returnService.getCustomerDataByTrackId(trackingId);
            return customerRecord;
        }

        return null;
    }


    @PostMapping("/pawnshop/updateReturnData")
    @ResponseBody
    public void updateReturnData(@RequestParam String trackingId,@RequestParam String returnedDate,
                                 @RequestParam String totalReturnAmount,@RequestParam String interestAmount ) {


        returnService.updateCustomerReturnData(trackingId,returnedDate,totalReturnAmount,interestAmount);

    }



    @PostMapping("/pawnshop/globalSearch")
    @ResponseBody
    public List<CustomerData> doGlobalSearch(@RequestParam String startDate, @RequestParam String endDate) {


        return globalSearchService.displayGlobalSearchData(startDate,endDate);

    }


    @PostMapping("/pawnshop/generateDailyReport")
    @ResponseBody
    public ResponseEntity<Resource> generateDailyReport() {

        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);

        ResponseEntity<Resource> report = reportService.generateDailyReport(sqlDate);

        return report;
    }

    @PostMapping("/pawnshop/generateTimeBasedReport")
    public ResponseEntity<Resource> generateTimeBasedReport( @RequestParam String startDate,
                                                                        @RequestParam String endDate) {
        ResponseEntity<Resource> res = reportService.generateTimeBasedReport(startDate,endDate);
        return res;
    }

    @PostMapping("/pawnshop/generateCustomReport")
    public ResponseEntity<Resource> generateCustomReport( @RequestParam String name,
                 @RequestParam String place,@RequestParam String requestedDate,@RequestParam String returnedDate,
                              @RequestParam String materialNature,@RequestParam String materialType,@RequestParam String parameterType) {


        ResponseEntity<Resource> res = reportService.generateCustomReport(name,place,requestedDate,returnedDate,materialNature,materialType,parameterType);
        return res;
    }
}
