package com.shape.query;

import lombok.Data;

/**
 * 通用查询对象 本来是给分页用的 不过暂时用不上了
 */
@Data
public class BaseQuery {

    /**
     * 当前第几页
     */
    private Integer pageIndex = 1;
    /**
     * 一页显示多少条数据
     */
    private Integer pageSize = 20;
}
