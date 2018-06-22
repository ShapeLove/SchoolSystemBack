package com.shape.query;


import lombok.Data;

@Data
public class HomeworkQuery extends BaseQuery{
    private String teacherId;
    private Integer classId;
    private String studentId;
}
