package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.FavoriteMapper;
import com.bupt.buptcar.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    FavoriteMapper favoriteMapper;

    public String addToFavorite(Integer userID, Integer carID) {
        boolean isFavorite = favoriteMapper.isFavorite(userID, carID);
        if (isFavorite){
            return "已经收藏过了！";
        }
        favoriteMapper.addToFavorite(userID, carID);
        return "收藏成功！";
    }

    public List<Car> getByBuyerID(Integer userID) {
        return favoriteMapper.getByBuyerID(userID);
    }

    public void deleteByBuyerIDCarID(Integer userID, Integer carID){
        favoriteMapper.deleteByBuyerIDCarID(userID, carID);
    }
}
