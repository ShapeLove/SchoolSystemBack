package com.shape.dao;

import com.shape.entity.Leave;
import com.shape.query.LeaveQuery;

import java.util.List;

/**
 * 缺勤数据库接口
 */
public interface LeaveDao {
    /**
     * 插入缺勤记录
     * @param leave
     * @return
     */
    int insertLeave(Leave leave);

    /**
     * 查询缺勤记录
     * @param query
     * @return
     */
    List<Leave> queryLeave(LeaveQuery query);
}
