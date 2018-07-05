package com.shape.web.controller;

import com.shape.entity.Class;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Teacher;
import com.shape.query.TeacherQuery;
import com.shape.web.dto.CustomClass;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.TeacherService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Teacher teacher) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (!customUser.getRole().equals("manager")) {
            jsonResult.setMessage("只有管理员才能添加教师");
        }else {
            jsonResult = teacherService.add(teacher);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<PageResult<Teacher>> query(@RequestBody TeacherQuery query) {
        return teacherService.query(query);
    }

    @RequestMapping(value = "/setmaster", method = RequestMethod.POST)
    public JsonResult setMaster(@RequestBody Class cla) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("manager")) {
            jsonResult = teacherService.setMaster(cla);
        }else {
            jsonResult.setMessage("只有管理员才能设置班主任");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/setclass", method = RequestMethod.POST)
    public JsonResult setClass(@RequestBody ClassTeacher classTeacher) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("manager")) {
            jsonResult = teacherService.setTeacherToClass(classTeacher);
        }else {
            jsonResult.setMessage("只有管理员才能设置教师所属班级");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/classInfo", method = RequestMethod.POST)
    public JsonResult<CustomClass> classInfo (@RequestBody ClassTeacher classTeacher) {
        return teacherService.getClassInfo(classTeacher.getClassId());
    }
}
