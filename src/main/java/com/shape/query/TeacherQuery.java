package com.shape.query;

import lombok.Data;

@Data
public class TeacherQuery extends BaseQuery {
    private String teacherId;
    private String teacherName;
}
