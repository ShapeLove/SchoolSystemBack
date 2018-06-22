package com.shape.entity;

import lombok.Data;

/**
 * 班级与教师对应关系实体
 */
@Data
public class ClassTeacher {
    private Integer id;
    private Integer classId;
    private String teacherId;
}
