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
        CustomUser customUser = WebContext.getUserFromSession();
        leave.setTeacherId(customUser.getUserName());
        return leaveService.addLeave(leave);
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
