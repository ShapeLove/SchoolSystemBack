package com.shape.query;

import lombok.Data;

@Data
public class ScoreQuery extends BaseQuery {
    private String studentId;
    private String studentName;
    private Integer classId;
}
