package com.source.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRACKING_NUMBER")
public class TrackingNumber {

    @Id
    @Column(name="TRACKING_ID")
    private  int trackingId;

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }
}
