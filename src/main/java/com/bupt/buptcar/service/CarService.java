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

    public List<Car> getCarList(Car car){
        return carMapper.getCarList(car);
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
