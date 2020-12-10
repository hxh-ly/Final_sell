package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    private Integer id;
    private Date signdate;
    private Integer status;
    private Client client;
    private Staff staff;
    private List<Orders> orders;
}
