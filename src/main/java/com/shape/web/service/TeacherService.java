package com.shape.web.service;

import com.shape.entity.Class;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Teacher;
import com.shape.query.TeacherQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;

/**
 * 教师逻辑接口
 */
public interface TeacherService {

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    JsonResult add(Teacher teacher);

    /**
     * 查询教师
     * @param query
     * @return
     */
    JsonResult<PageResult<Teacher>> query(TeacherQuery query);

    JsonResult setMaster(Class cla);

    JsonResult setTeacherToClass(ClassTeacher classTeacher);
}
