package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.CarMapper;
import com.bupt.buptcar.dao.PaymentMapper;
import com.bupt.buptcar.dao.ReserveMapper;
import com.bupt.buptcar.pojo.Payment;
import com.bupt.buptcar.pojo.Payment;
import com.bupt.buptcar.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    ReserveMapper reserveMapper;

    public String addPayment(Integer buyerID, Integer carID) {
        Integer salerID = carMapper.getSalerIDByCarID(carID);
        if (salerID == buyerID){
            return "不可以购买自己的车！";
        }
        boolean isPayment = paymentMapper.isPayment(buyerID, carID);
        if (isPayment){
            return "重复发起订单了！";
        }
        Payment payment = new Payment();
        payment.setCarID(carID);
        payment.setBuyerID(buyerID);
        payment.setSalerID(salerID);
        payment.setTotal_fee(carMapper.getPriceByCarID(carID));
        paymentMapper.addPayment(payment);
        return "发起订单成功！";
    } 

    public List<Payment> getByBuyerID(Integer userID) { 
        return paymentMapper.getByBuyerID(userID);
    }

    public List<Payment> getBySalerID(Integer userID) {
        return paymentMapper.getBySalerID(userID);
    }


    public void cancelByPaymentID(Integer paymentID) {
        paymentMapper.setStateByPaymentID(4, paymentID);
    }

    public void confirmByPaymentID(Integer paymentID) {
        paymentMapper.setStateByPaymentID(1, paymentID);
    }

    public void finishByPaymentID(Integer paymentID) {
        paymentMapper.setStateByPaymentID(3, paymentID);
    }


    public void payByPaymentID(Integer paymentID) {
        paymentMapper.setStateByPaymentID(2, paymentID);
        paymentMapper.setPayTimeByPaymentID(paymentID);
        Integer carID = paymentMapper.getCarIDByPaymentID(paymentID);
        // 设置汽车状态 已被买
        carMapper.setSaledByCarID(paymentMapper.getCarIDByPaymentID(paymentID));

        // reserve设为取消或完成
        reserveMapper.setCancelByCarID(carID);
        // payment设为取消或完成
        paymentMapper.setCancelByCarID(carID);
    }
}