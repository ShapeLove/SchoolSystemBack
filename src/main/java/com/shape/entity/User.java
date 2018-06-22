package com.shape.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String userPassword;
    private String role;
}
