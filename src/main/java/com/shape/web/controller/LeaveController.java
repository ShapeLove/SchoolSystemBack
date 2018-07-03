package com.shape.web.controller;

import com.shape.entity.Leave;
import com.shape.query.LeaveQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.LeaveService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Leave leave) {
        JsonResult jsonResult =JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if(customUser.getRole().equals("master") || customUser.getRole().equals("teacher")) {
            leave.setTeacherId(customUser.getUserName());
            jsonResult = leaveService.addLeave(leave);
        }else {
            jsonResult.setMessage("只有教师才能添加缺勤记录");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<List<Leave>> query(@RequestBody LeaveQuery query) {
        CustomUser user = WebContext.getUserFromSession();
        if (user.getRole().equals("teacher") || user.getRole().equals("master")) {
            query.setTeacherId(user.getUserName());
        }else if (user.getRole().equals("student")) {
            query.setStudentId(user.getUserName());
        }
        return leaveService.queryLeave(query);
    }
}
