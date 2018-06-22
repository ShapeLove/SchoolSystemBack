package com.shape.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Activity {
    private Integer id;
    private Integer classId;
    private Date activityDate;
    private String attendPerson;
    private String content;
    private String organizePerson;
}
