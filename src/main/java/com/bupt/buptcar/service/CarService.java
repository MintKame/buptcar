package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.CarMapper;
import com.bupt.buptcar.dao.FavoriteMapper;
import com.bupt.buptcar.dao.SeriesMapper;
import com.bupt.buptcar.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarMapper carMapper;

    @Autowired
    SeriesMapper seriesMapper;

    @Autowired
    FavoriteMapper favoriteMapper;

    public List<Car> getCarList(Car car, Integer pageID){
        int pageSize = 100; // 每页100条
        int pageIndex = (pageID-1)*100; // 从第 pageIndex 条开始显示
        if(pageIndex < 0) pageIndex = 0;
        return carMapper.getCarList(car, pageIndex, pageSize);
    }

    public Car getById(Integer carID){
        return carMapper.getById(carID);
    }

    public List<Car> getBySalerID(Integer salerID){
        return carMapper.getBySalerID(salerID);
    }

    public void deleteByID(Integer carID) {
        favoriteMapper.deleteByCarID(carID);
        carMapper.deleteByID(carID);
    }

    public String addCar(Car car, Integer salerID) {
        Integer seriesID = seriesMapper.getSeriesID(car.getBrand(), car.getSeriesName());
        if (seriesID == null){
            return "添加失败：该系列不存在";
        }
        car.setSeriesID(seriesID);
        car.setSalerID(salerID);
        carMapper.addCar(car);
        return "成功添加";
    }
}
