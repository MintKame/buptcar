package com.bupt.buptcar.pojo;

import lombok.Data;

@Data
public class Reserve {
    // 数据库中，预约的信息
    private Integer reserveID;

    private Integer carID;

    private Integer buyerID;

    private Integer salerID;

    private Integer state; // 预约状态（0：已创建，1：已确认，2：已完成看车，3：已取消）

    private String gmt_reserve;

    // 查询预约时，额外提供的信息（汽车描述，照片）
    private String addr;

    private String imgURL;

    private String carDesc;

    private String buyerMobile;

    private String salerMobile;
}
