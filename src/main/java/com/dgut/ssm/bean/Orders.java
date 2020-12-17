package com.dgut.ssm.bean;

import lombok.Data;

import java.util.List;

@Data
public class Orders {
    //订单id
    private Integer id;
    private List<Goods> goods;

}
