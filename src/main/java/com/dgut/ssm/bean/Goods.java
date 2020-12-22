package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer id;
    private String goodsName;
    private Double price;
    private Integer amount;
    //根据 receipt的status 是否发货
    private Integer isSend;
    //根据 中间表的状态 是否已经生成发货单
    private  Integer isGenerate;
    private String category;

    public Goods(Integer id, String goodsName, Double price, Integer amount, String category) {
        this.id = id;
        this.goodsName = goodsName;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }
}
