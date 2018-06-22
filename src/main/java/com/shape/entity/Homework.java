package com.shape.entity;

import lombok.Data;

@Data
public class Homework {
    private Integer id;
    private Integer classId;
    private String content;
    private String teacherId;
    private String studentId;
    private String teacherRevert;
    private String studentRevert;
}
