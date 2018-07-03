package com.shape.dao;

import com.shape.entity.Class;
import com.shape.query.ClassQuery;

import java.util.List;

/**
 * 班级数据库接口
 */
public interface ClassDao {
    /**
     * 插入班级
     * @param cla
     * @return
     */
    int insertClass(Class cla);

    /**
     * 查看班级信息
     * @return
     */
    List<Class> queryClass(ClassQuery query);


    /**
     * 修改班级信息
     * @param cla 可以根据传入的id 定位数据库记录 并修改班级名称和班主任的教师id
     * @return
     */
    int updateClass(Class cla);
}
