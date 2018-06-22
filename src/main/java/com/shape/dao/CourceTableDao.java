package com.shape.dao;

import com.shape.entity.CourceTable;
import com.shape.query.CourceTableQuery;

import java.util.List;

/**
 * 课程表数据库接口
 */
public interface CourceTableDao {
    int insertCourceTable(CourceTable courceTable);
    List<CourceTable> queryCourceTable(CourceTableQuery query);
}
