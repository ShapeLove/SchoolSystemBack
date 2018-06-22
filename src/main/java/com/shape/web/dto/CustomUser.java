package com.shape.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomUser {
    private int id;
    private String userName;
    private String userPassword;
    private String role;
    private int classId;
    private List<Integer> teachClassIdList;
    private int masterClassId;
}
