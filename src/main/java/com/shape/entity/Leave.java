package com.shape.entity;

import lombok.Data;

import java.util.Date;

/**
 * 请假实体
 */
@Data
public class Leave {
    private Integer id;
    private String teacherId;
    private String subject;
    private Date leaveDate;
    private String reason;
    private String studentId;
}
