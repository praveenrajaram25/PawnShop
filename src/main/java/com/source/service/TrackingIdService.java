package com.source.service;


import com.source.model.TrackingNumber;

import java.util.List;


public interface TrackingIdService {

    List<TrackingNumber> generateTrackingId();

    void updateTrackingId(int oldTrackId,int newTrackId);
}
