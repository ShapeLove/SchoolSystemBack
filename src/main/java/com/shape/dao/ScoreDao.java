package com.shape.dao;

import com.shape.entity.CustomScore;
import com.shape.entity.Score;
import com.shape.query.ScoreQuery;

import java.util.List;

/**
 * 成绩数据库接口
 */
public interface ScoreDao {
    int insertScore(Score score);
    List<CustomScore> queryScore(ScoreQuery query);
}
