package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitingList {
    private Integer id;
    private String goodsName;
    private Integer num;
}
