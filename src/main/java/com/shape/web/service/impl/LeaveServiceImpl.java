package com.shape.web.service.impl;

import com.shape.dao.LeaveDao;
import com.shape.entity.Leave;
import com.shape.query.LeaveQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public JsonResult addLeave(Leave leave) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            leaveDao.insertLeave(leave);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<List<Leave>> queryLeave(LeaveQuery query) {
        JsonResult<List<Leave>> jsonResult = JsonResult.falseResult();
        try {
            List<Leave> leaveList = leaveDao.queryLeave(query);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(leaveList);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
