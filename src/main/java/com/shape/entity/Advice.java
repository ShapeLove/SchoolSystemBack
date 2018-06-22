package com.shape.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Advice {
    private Integer id;
    private Date adviceDate;
    private String content;
}
