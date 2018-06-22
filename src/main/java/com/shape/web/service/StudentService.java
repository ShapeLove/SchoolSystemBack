package com.shape.web.service;

import com.shape.entity.Student;
import com.shape.query.StudentQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;

/**
 * 学生逻辑接口
 */
public interface StudentService {

    /**
     * 添加学生
     * @param student
     * @return
     */
    JsonResult addStudent(Student student);

    /**
     * 查询学生
     * @param query 可以根据学生姓名等信息模糊查询 （分页暂不支持）
     * @return
     */
    JsonResult<PageResult<Student>> queryStudent(StudentQuery query);
}
