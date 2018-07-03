package com.shape.dao;

import com.shape.entity.Evaluate;
import com.shape.query.EvaluateQuery;

import java.util.List;

/**
 * 月评价数据库接口
 */
public interface EvaluateDao {
    /**
     * 插入月评价
     * @param evaluate
     * @return
     */
    int insertEvaluate(Evaluate evaluate);

    /**
     * 查询月评价
     * @param query
     * @return
     */
    List<Evaluate> queryEvaluate(EvaluateQuery query);

    /**
     * 更新月评价
     * @param evaluate 根据id定位要修改的记录 可以修改教师和学生家长的评价
     * @return
     */
    int updateEvaluate(Evaluate evaluate);
}
