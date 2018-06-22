package com.shape.entity;

import lombok.Data;

@Data
public class Teacher {
    private String id;
    private String name;
    private Integer sex;
    private Integer age;
    /**
     * 职务  教什么的 语文英语
     */
    private String level;
    private String phone;
    private String email;
    private String address;
}
