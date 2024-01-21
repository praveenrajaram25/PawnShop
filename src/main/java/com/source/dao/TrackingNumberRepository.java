package com.source.dao;

import com.source.model.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrackingNumberRepository  extends JpaRepository<TrackingNumber,Integer> {

    @Modifying
    @Query("UPDATE TrackingNumber  SET trackingId = :newTrackId WHERE trackingId = :oldTrackId")
    void updateTrackingId(@Param("oldTrackId") int oldTrackId, @Param("newTrackId") int newTrackId);
}
