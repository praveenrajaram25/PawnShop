package com.source.serviceImpl;

import com.source.dao.CustomerDataRepository;
import com.source.model.CustomerData;
import com.source.service.ReturnService;
import com.source.utils.PawnShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReturnServiceImpl implements ReturnService {


    @Autowired
    CustomerDataRepository customerRepo;

    @Autowired
    PawnShopUtils utils;
    @Override
    public CustomerData getCustomerDataByTrackId(int trackId) {

        return customerRepo.findByTrackingId(trackId);
    }

    @Override
    public void updateCustomerReturnData(String trackingId, String returnedDate, String totalReturnAmount, String interestAmount) {
        int trackId = Integer.parseInt(trackingId);
        java.sql.Date returnDate=utils.convertStringToDate(returnedDate);
        String status="Returned";
        String result="The item has been returned Successfully";
        customerRepo.updateCustomerReturnData(trackId,returnDate,totalReturnAmount,interestAmount,status,result);


    }
}
