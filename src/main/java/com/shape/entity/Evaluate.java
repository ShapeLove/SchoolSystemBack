package com.shape.entity;

import lombok.Data;

@Data
public class Evaluate {
    private Integer id;
    private String teacherId;
    private String studentId;
    private String teacherEvaluate;
    private String studentEvaluate;
}
