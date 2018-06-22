package com.shape.entity;

import lombok.Data;

@Data
public class CustomScore {
    private Integer id;
    private String studentId;
    private String studentName;
    private Integer chinese;
    private Integer math;
    private Integer english;
    private Integer music;
    private Integer gym;
}
