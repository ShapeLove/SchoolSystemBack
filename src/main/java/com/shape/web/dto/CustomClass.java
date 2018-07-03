package com.shape.web.dto;

import com.shape.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class CustomClass {
    /**
     * 班级id
     */
    private int classId;
    /**
     * 班主任信息
     */
    private Teacher masterTeacher;
    /**
     * 任课教师信息
     */
    private List<Teacher> classTeacher;
}
