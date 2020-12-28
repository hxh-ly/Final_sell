package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private  Integer id;
    @NotEmpty(message = "{client.name.required}")
    private String name;
    @NotEmpty(message = "号码不能为空")
    @Pattern(regexp = "^[1-9]+")
    private String phone;
    @NotEmpty(message = "地址不能为空")

    private  String location;
}
