package com.shape.web.service;

import com.shape.entity.CustomScore;
import com.shape.entity.Score;
import com.shape.query.ScoreQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;

/**
 * 成绩管理逻辑接口
 */
public interface ScoreService {
    JsonResult add(Score score);

    JsonResult<PageResult<CustomScore>> query(ScoreQuery query);
}
