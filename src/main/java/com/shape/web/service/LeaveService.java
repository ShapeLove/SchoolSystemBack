package com.shape.web.service;

import com.shape.entity.Leave;
import com.shape.query.LeaveQuery;
import com.shape.web.dto.JsonResult;

import java.util.List;

public interface LeaveService {
    JsonResult addLeave(Leave leave);
    JsonResult<List<Leave>> queryLeave(LeaveQuery query);
}
