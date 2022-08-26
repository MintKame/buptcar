package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.CarMapper;
import com.bupt.buptcar.dao.ReserveMapper;
import com.bupt.buptcar.pojo.Car;
import com.bupt.buptcar.pojo.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {
    @Autowired
    ReserveMapper reserveMapper;

    @Autowired
    CarMapper carMapper;

    public String addReserve(Integer buyerID, Integer carID, String gmt_reserve) {
        Integer salerID = carMapper.getSalerIDByCarID(carID);
        if (salerID == buyerID){
            return "不可以预约自己的车！";
        }
        boolean isReserve = reserveMapper.isReserve(buyerID, carID);
        if (isReserve){
            return "已经预约过了！";
        }
        Reserve reserve = new Reserve();
        reserve.setCarID(carID);
        reserve.setBuyerID(buyerID);
        reserve.setSalerID(salerID);
        reserve.setGmt_reserve(gmt_reserve);
        reserveMapper.addReserve(reserve);
        return "预约成功！";
    }

    public List<Reserve> getByBuyerID(Integer userID) {
        return reserveMapper.getByBuyerID(userID);
    }

    public List<Reserve> getBySalerID(Integer userID) {
        return reserveMapper.getBySalerID(userID);
    }

    public void cancelByReserveID(Integer reserveID) {
        reserveMapper.setStateByReserveID(3, reserveID);
    }

    public void confirmByReserveID(Integer reserveID) {
        reserveMapper.setStateByReserveID(1, reserveID);
    }

    public void finishByReserveID(Integer reserveID) {
        reserveMapper.setStateByReserveID(2, reserveID);
    }
}
