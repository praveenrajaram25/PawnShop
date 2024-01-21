package com.source.service;

import com.source.model.CustomerData;

public interface ReturnService {
    CustomerData getCustomerDataByTrackId(int trackingId);

    void updateCustomerReturnData(String trackingId, String returnedDate, String totalReturnAmount, String interestAmount);
}
