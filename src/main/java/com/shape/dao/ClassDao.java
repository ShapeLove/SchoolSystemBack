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


    int updateClass(Class cla);
}
