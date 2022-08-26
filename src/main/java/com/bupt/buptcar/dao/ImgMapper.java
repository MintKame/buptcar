package com.bupt.buptcar.dao;

import com.bupt.buptcar.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImgMapper {
    void setImgByCarID(@Param("carID") Integer carID, @Param("img") String mainImgName);

    void addImg(@Param("carID") Integer carID,  @Param("url")String imgName);

    List<String> getImgByCarID(Integer carID);
}
