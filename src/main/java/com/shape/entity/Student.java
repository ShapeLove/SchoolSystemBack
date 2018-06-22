package com.shape.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Student {
    private String id;
    private String name;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
        入学时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date intake;
    private String phone;
    private String address;
    private Integer classId;
}
