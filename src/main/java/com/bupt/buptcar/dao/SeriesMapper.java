package com.bupt.buptcar.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeriesMapper {
    public Integer getSeriesID(@Param("brand")String brand, @Param("seriesName")String seriesName);

    public List<String> getBrandList();

    public List<String> getSeriesNameList();
}
