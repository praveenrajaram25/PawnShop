package com.source.dao;

import com.source.model.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Repository
@Transactional
public interface CustomerDataRepository extends JpaRepository<CustomerData,Integer> {

    CustomerData findByTrackingId(Integer trackingId);

    @Modifying
    @Query("UPDATE CustomerData  SET returnedAmount = :returnAmount,interestAmount=:interestAmount,returnedDate=:returnDate,status=:status,result=:result WHERE trackingId = :trackingId")
    void updateCustomerReturnData(@Param("trackingId") int trackingId, @Param("returnDate") Date returnDate, @Param("returnAmount") String returnAmount, @Param("interestAmount") String interestAmount,@Param("status") String status,@Param("result") String result);


    List<CustomerData> findByRequestedDateBetween(Date startDate, Date endDate);

    List<CustomerData> findByReturnedDateBetween(Date startDate, Date endDate);

    List<CustomerData> findByRequestedDate(Date requestedDate);

    List<CustomerData> findByReturnedDate(Date requestedDate);

    List<CustomerData> findByName(String name);

    List<CustomerData> findByPlace(String place);

    List<CustomerData> findByMaterialNature(String materialNature);

    List<CustomerData> findByMaterialType(String materialType);


}
