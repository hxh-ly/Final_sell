package com.dgut.ssm.bean;

import lombok.Data;

@Data
public class Delivery {
    private  Integer id;
    private  String name;
    private   Integer dnumber;
    private Integer quantity;
    private String location;
    private String dstatus;

}
