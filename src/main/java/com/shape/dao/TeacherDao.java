package com.shape.dao;

import com.shape.entity.Teacher;
import com.shape.query.TeacherQuery;

import java.util.List;

/**
 * 教师数据库接口
 */
public interface TeacherDao {

    /**
     * 插入教师信息
     * @param teacher
     * @return
     */
    int insertTeacher(Teacher teacher);

    /**
     * 查询教师信息
     * @param query
     * @return
     */
    List<Teacher> queryTeacher(TeacherQuery query);
}
