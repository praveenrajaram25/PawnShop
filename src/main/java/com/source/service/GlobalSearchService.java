package com.source.service;

import com.source.model.CustomerData;

import java.util.List;

public interface GlobalSearchService {
    List<CustomerData> displayGlobalSearchData(String startDate, String endDate);
}
