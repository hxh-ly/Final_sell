package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private Integer id;
    private  String goodsName;
    private  int num;
    private Date deliverDate;
    private String userName;
}
