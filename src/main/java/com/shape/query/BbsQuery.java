package com.shape.query;

import lombok.Data;

/**
 * 留言板查询对象 表示可以按照什么数据库字段去查询数据
 */
@Data
public class BbsQuery {
    /**
     * 教师教工号
     */
    private String teacherId;
    /**
     * 学生学号
     */
    private String studentId;
}
