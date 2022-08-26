package com.bupt.buptcar.dao;

import com.bupt.buptcar.pojo.Car;
import com.bupt.buptcar.pojo.Payment;
import com.bupt.buptcar.pojo.Reserve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    Boolean isPayment(@Param("buyerID")Integer buyerID, @Param("carID")Integer carID);

    void addPayment(Payment payment);

    List<Payment> getByBuyerID(Integer userID);

    List<Payment> getBySalerID(Integer userID);

    void setStateByPaymentID(@Param("state")Integer state, @Param("paymentID")Integer paymentID);

    void setPayTimeByPaymentID(Integer paymentID);

    Integer getCarIDByPaymentID(Integer paymentID);

    void setCancelByCarID(Integer carID);
}
