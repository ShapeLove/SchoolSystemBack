package com.shape.dao;

import com.shape.entity.Leave;
import com.shape.query.LeaveQuery;

import java.util.List;

/**
 * 缺勤数据库接口
 */
public interface LeaveDao {
    int insertLeave(Leave leave);
    List<Leave> queryLeave(LeaveQuery query);
}
