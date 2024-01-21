package com.source.serviceImpl;

import com.source.dao.CustomerDataRepository;
import com.source.model.CustomerData;
import com.source.service.GlobalSearchService;
import com.source.utils.PawnShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class GlobalSearchServiceImpl implements GlobalSearchService {


    @Autowired
    PawnShopUtils utils;

    @Autowired
    CustomerDataRepository customerRepo;

    @Override
    public List<CustomerData> displayGlobalSearchData(String startDate, String endDate) {
        Date staringDate = utils.convertStringToDate(startDate);
        Date endingDate = utils.convertStringToDate(endDate);
        return customerRepo.findByRequestedDateBetween(staringDate,endingDate);
    }
}
