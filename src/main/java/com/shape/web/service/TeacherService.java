package com.shape.web.service;

import com.shape.entity.Class;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Teacher;
import com.shape.query.TeacherQuery;
import com.shape.web.dto.CustomClass;
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

    /**
     * 给班级设置班主任
     * @param cla
     * @return
     */
    JsonResult setMaster(Class cla);

    /**
     * 给班级设置教师
     * @param classTeacher
     * @return
     */
    JsonResult setTeacherToClass(ClassTeacher classTeacher);

    /**
     * 班级所有教师信息 包括班主任和任课教师
     * @param classId
     * @return
     */
    JsonResult<CustomClass> getClassInfo(int classId);
}
