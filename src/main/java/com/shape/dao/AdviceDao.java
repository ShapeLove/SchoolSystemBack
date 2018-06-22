package com.shape.dao;

import com.shape.entity.Advice;

import java.util.List;

/**
 * 通知栏数据库接口
 */
public interface AdviceDao {
    /**
     * 插入通知
     * @param advice
     * @return
     */
    int insertAdvice(Advice advice);

    /**
     * 查看所有通知
     * @return
     */
    List<Advice> queryAdvice();
}
