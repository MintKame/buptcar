package com.bupt.buptcar.pojo;

import lombok.Data;

/** 搜索页汽车 */
@Data
public class Car {
    // 数据库中存储的信息
    private Integer carID;

    private Integer seriesID;

    private Integer salerID;

    private String region;

    private String addr;

    private Boolean isSaled;

    private Double price;

    private String regDate;

    private String gearbox;

    private Double mkm;

    private String img;

    private String description;

    // 查询时的筛选条件
    private Double lowPrice;

    private Double highPrice;

    private Integer age;

    // 以下是本汽车的系列的信息
    private String brand;

    private String seriesName;

    private String nation;

    private String bodyType;

    private String fuelType;

    private Integer seatNum;
}
