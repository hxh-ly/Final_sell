package com.dgut.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private  Integer id;
    @NotEmpty(message = "{staff.name.required}")
    private String name;
    @Length(min=11,max=11,message = "{staff.phone.required}")
    private String phone;
}
