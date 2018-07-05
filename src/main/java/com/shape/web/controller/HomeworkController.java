package com.shape.web.controller;

import com.shape.entity.Homework;
import com.shape.query.HomeworkQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.HomeworkService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Homework homework) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("master")) {
           homework.setTeacherId(customUser.getUserName());
           homework.setClassId(customUser.getMasterClassId());
           jsonResult = homeworkService.add(homework);
        }else {
            jsonResult.setMessage("只有班主任才能发布作业");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public JsonResult<PageResult<Homework>> query(@RequestBody HomeworkQuery homeworkQuery) {
        CustomUser user = WebContext.getUserFromSession();
        if (user.getRole().equals("master")) {
            homeworkQuery.setTeacherId(user.getUserName());
        }else if (user.getRole().equals("student")) {
            homeworkQuery.setStudentId(user.getUserName());
        }
        return homeworkService.quest(homeworkQuery);
    }

    @RequestMapping(value = "revert", method = RequestMethod.POST)
    public JsonResult revert(@RequestBody Homework homework) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if(customUser.getRole().equals("master") || customUser.getRole().equals("student")) {
            jsonResult = homeworkService.revertHomework(homework);
        }else {
            jsonResult.setMessage("只有班主任或者学生家长能够回复");
        }
        return jsonResult;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonResult delete(@RequestBody List<Integer> homeworkIdList) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("master")) {
            jsonResult = homeworkService.deleteHomework(homeworkIdList);
        }else {
            jsonResult.setMessage("只有班主任才能删除作业");
        }
        return jsonResult;
    }
}
