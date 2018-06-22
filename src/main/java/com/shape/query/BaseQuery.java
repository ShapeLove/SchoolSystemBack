package com.shape.query;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer pageIndex = 1;
    private Integer pageSize = 20;
}
