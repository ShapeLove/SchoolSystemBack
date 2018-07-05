package com.shape.dao;

import com.shape.entity.CourceTable;
import com.shape.query.CourceTableQuery;

import java.util.List;

/**
 * 课程表数据库接口
 */
public interface CourceTableDao {
    /**
     * 插入课程表
     * @param courceTable
     * @return
     */
    int insertCourceTable(CourceTable courceTable);

    /**
     * 查询课程表
     * @param query
     * @return
     */
    List<CourceTable> queryCourceTable(CourceTableQuery query);

    List<CourceTable> queryCourceTableByWeekJie(CourceTableQuery query);

    int updateCourceTable(CourceTable courceTable);
}
