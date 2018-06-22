package com.shape.dao;

import com.shape.entity.ClassTeacher;
import com.shape.query.ClassTeacherQuery;

import java.util.List;

/**
 * 班级教师关联数据库接口
 */
public interface ClassTeacherDao {

    /**
     * 插入班级与教师关系
     * @param classTeacher
     * @return
     */
    int insertClassTeacher(ClassTeacher classTeacher);

    /**
     * 查看班级与教师关系
     * @return
     */
    List<ClassTeacher> queryClassTeacher(ClassTeacherQuery query);
}
