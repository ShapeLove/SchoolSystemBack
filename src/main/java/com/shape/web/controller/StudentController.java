package com.shape.web.controller;

import com.shape.entity.Student;
import com.shape.query.StudentQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.StudentService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Student student) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("master")) {
            student.setClassId(customUser.getMasterClassId());
            jsonResult = studentService.addStudent(student);
        }else {
            jsonResult.setMessage("只有班主任才能添加学生信息");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<PageResult<Student>> queryStudent(@RequestBody StudentQuery query) {
        return studentService.queryStudent(query);
    }
}
