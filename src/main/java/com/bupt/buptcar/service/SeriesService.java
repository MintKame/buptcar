package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.CarMapper;
import com.bupt.buptcar.dao.FavoriteMapper;
import com.bupt.buptcar.dao.SeriesMapper;
import com.bupt.buptcar.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {
    @Autowired
    SeriesMapper seriesMapper;

    public List<String> getBrandList(){
        return seriesMapper.getBrandList();
    }

    public List<String> getSeriesNameList(){
        return seriesMapper.getSeriesNameList();
    }
}