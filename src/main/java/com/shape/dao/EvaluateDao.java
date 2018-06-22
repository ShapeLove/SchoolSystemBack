package com.shape.dao;

import com.shape.entity.Evaluate;
import com.shape.query.EvaluateQuery;

import java.util.List;

/**
 * 月评价数据库接口
 */
public interface EvaluateDao {
    int insertEvaluate(Evaluate evaluate);
    List<Evaluate> queryEvaluate(EvaluateQuery query);
    int updateEvaluate(Evaluate evaluate);
}
