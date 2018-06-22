package com.shape.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private int pageIndex = 1;
    private int pageSize = 20;
    private List<T> dataList;
}
