package com.bupt.buptcar.pojo;

import lombok.Data;

@Data
public class Payment {
    private Integer paymentID;

    private Integer carID;

    private Integer buyerID;

    private Integer salerID;

    private Integer state;    // 订单状态（0：已创建，1：已确认，2：已支付，3：已提车，4：已取消）

    private Double total_fee;

    private String gmt_pay;

    private Integer pay_type;    // 支付类型（1：微信 2：支付宝）

    // 查询预约时，额外提供的信息（汽车描述，照片）
    private String addr;

    private String imgURL;

    private String carDesc;

    private String buyerMobile;

    private String salerMobile;
}