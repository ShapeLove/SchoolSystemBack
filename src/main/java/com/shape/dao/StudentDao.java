package com.shape.dao;

import com.shape.entity.Student;
import com.shape.query.StudentQuery;

import java.util.List;

/**
 * 学生数据库接口
 */
public interface StudentDao {
    int insertStudent(Student student);
    List<Student> queryStudent(StudentQuery query);
}
