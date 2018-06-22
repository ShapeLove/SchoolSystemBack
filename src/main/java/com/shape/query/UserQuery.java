package com.shape.query;

import lombok.Data;

@Data
public class UserQuery extends BaseQuery {
    private String userPassword;
    private String userName;
    private String role;
}
