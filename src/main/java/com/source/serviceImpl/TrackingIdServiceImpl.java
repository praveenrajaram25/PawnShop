package com.source.serviceImpl;


import com.source.dao.TrackingNumberRepository;
import com.source.model.TrackingNumber;
import com.source.service.TrackingIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrackingIdServiceImpl implements TrackingIdService {

    private static final Logger logger = LoggerFactory.getLogger(TrackingIdServiceImpl.class);

    @Autowired
    TrackingNumberRepository trackingNumberRepository;
    @Override
    public List<TrackingNumber> generateTrackingId() {

        return  trackingNumberRepository.findAll();
    }

    @Override
    public void updateTrackingId(int oldTrackId,int newTrackId) {
        boolean updateId;
        try{
            trackingNumberRepository.updateTrackingId(oldTrackId,newTrackId);
            updateId=true;
        }catch(Exception e){
            updateId=false;
            logger.info("Exception occurred while updating TrackingId"+e);
        }
        if(updateId){
            logger.info("TrackingId Successfully Updated");
        }
    }
}
