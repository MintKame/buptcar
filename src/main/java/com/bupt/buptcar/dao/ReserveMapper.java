package com.bupt.buptcar.dao;

import com.bupt.buptcar.pojo.Car;
import com.bupt.buptcar.pojo.Reserve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveMapper {
    Boolean isReserve(@Param("buyerID")Integer userID, @Param("carID")Integer carID);

    void addReserve(Reserve reserve);

    List<Reserve> getByBuyerID(Integer userID);

    List<Reserve> getBySalerID(Integer userID);

    void setStateByReserveID(@Param("state")Integer state, @Param("reserveID")Integer reserveID);

    void setCancelByCarID(Integer carID);
}
