package com.shape.entity;

import lombok.Data;

import java.util.Date;

/**
 * 留言板实体
 */
@Data
public class Bbs {
    private Integer id;
    private String studentId;
    private String teacherId;
    private Date bbsDate;
    private String content;
    private String teacherRevert;
}
