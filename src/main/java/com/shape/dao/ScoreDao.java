package com.shape.dao;

import com.shape.entity.CustomScore;
import com.shape.entity.Score;
import com.shape.query.ScoreQuery;

import java.util.List;

/**
 * 成绩数据库接口
 */
public interface ScoreDao {
    /**
     * 插入成绩信息
     * @param score
     * @return
     */
    int insertScore(Score score);

    /**
     * 查询成绩
     * @param query
     * @return
     */
    List<CustomScore> queryScore(ScoreQuery query);
}
