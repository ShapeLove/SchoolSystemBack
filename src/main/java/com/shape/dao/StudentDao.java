package com.shape.dao;

import com.shape.entity.Student;
import com.shape.query.StudentQuery;

import java.util.List;

/**
 * 学生数据库接口
 */
public interface StudentDao {
    /**
     * 插入学生信息
     * @param student
     * @return
     */
    int insertStudent(Student student);

    /**
     * 查询学生信息
     * @param query
     * @return
     */
    List<Student> queryStudent(StudentQuery query);
}
