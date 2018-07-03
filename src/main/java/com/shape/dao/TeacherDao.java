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

    /**
     * 根据classId查询出该班级所有教师 包括班主任 如果班主任教书的话
     * @param classId
     * @return
     */
    List<Teacher> queryTeacherByClassIdFromClassTeacher(int classId);

    /**
     * 根据classId查询出该班班主任信息
     * @param classId
     * @return
     */
    List<Teacher> queryTeacherByClassIdFromClass(int classId);
}
