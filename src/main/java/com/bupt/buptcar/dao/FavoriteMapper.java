package com.bupt.buptcar.dao;

import com.bupt.buptcar.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FavoriteMapper {
    Boolean isFavorite(@Param("buyerID")Integer userID, @Param("carID")Integer carID);

    void addToFavorite(@Param("buyerID")Integer userID, @Param("carID")Integer carID);

    List<Car> getByBuyerID(Integer userID);

    void deleteByCarID(Integer carID);

    void deleteByBuyerIDCarID(@Param("buyerID") Integer userID,@Param("carID") Integer carID);

}
