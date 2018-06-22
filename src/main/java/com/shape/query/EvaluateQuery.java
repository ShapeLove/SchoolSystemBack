package com.shape.query;

import lombok.Data;

@Data
public class EvaluateQuery extends BaseQuery {
    private String teacherId;
    private String studentId;
}
