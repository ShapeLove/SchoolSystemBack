package com.shape.web.service.impl;

import com.shape.dao.StudentDao;
import com.shape.dao.UserDao;
import com.shape.entity.Student;
import com.shape.entity.User;
import com.shape.query.StudentQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.StudentService;
import com.shape.web.util.Contant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addStudent(Student student) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            StudentQuery query = new StudentQuery();
            query.setStudentId(student.getId());
            List<Student> students = studentDao.queryStudent(query);
            if (students != null && students.size() > 0) {
                jsonResult.setMessage("学号已经存在");
            }else {
                studentDao.insertStudent(student);
                User user = new User();
                user.setRole("student");
                user.setUserName(student.getId());
                user.setUserPassword(Contant.USER_DEFAULT_PASSWORD);
                userDao.insertUser(user);
                jsonResult = JsonResult.successResult();
            }
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<PageResult<Student>> queryStudent(StudentQuery query) {
        JsonResult<PageResult<Student>> jsonResult = JsonResult.falseResult();
        try {
            List<Student> studentList = studentDao.queryStudent(query);
            PageResult<Student> pageResult = new PageResult<>(query.getPageIndex(), query.getPageSize(), studentList);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(pageResult);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
