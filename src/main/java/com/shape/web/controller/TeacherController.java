package com.shape.web.controller;

import com.shape.entity.Class;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Teacher;
import com.shape.query.TeacherQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.TeacherService;
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
        return teacherService.add(teacher);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<PageResult<Teacher>> query(@RequestBody TeacherQuery query) {
        return teacherService.query(query);
    }

    @RequestMapping(value = "/setmaster", method = RequestMethod.POST)
    public JsonResult setMaster(@RequestBody Class cla) {
        return teacherService.setMaster(cla);
    }

    @RequestMapping(value = "/setclass", method = RequestMethod.POST)
    public JsonResult setClass(@RequestBody ClassTeacher classTeacher) {
        return teacherService.setTeacherToClass(classTeacher);
    }
}
