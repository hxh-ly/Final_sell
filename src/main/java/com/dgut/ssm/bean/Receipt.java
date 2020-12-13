package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private  Integer expressId;
    private  Integer expressStatus;
    private  String location;
    private Goods goods;
}
