package com.bupt.buptcar.dao;

import com.bupt.buptcar.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.List;

@Mapper
public interface CarMapper {
    public List<Car> getCarList(Car car);

    public Car getById(Integer carId);

    public List<Car> getBySalerID(Integer salerID);

    public void deleteByID(Integer carID);

    public void addCar(Car car);

    public Integer getSalerIDByCarID(Integer carID);

    public String getAddrByCarID(Integer carID);

    public String getDescByCarID(Integer carID);

    public Double getPriceByCarID(Integer carID);

    public void setSaledByCarID(Integer carID);
}
