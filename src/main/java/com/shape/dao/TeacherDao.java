package com.shape.dao;

import com.shape.entity.Teacher;
import com.shape.query.TeacherQuery;

import java.util.List;

/**
 * 教师数据库接口
 */
public interface TeacherDao {
    int insertTeacher(Teacher teacher);
    List<Teacher> queryTeacher(TeacherQuery query);
}
