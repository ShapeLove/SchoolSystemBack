package com.shape.web.service;

import com.shape.entity.Evaluate;
import com.shape.query.EvaluateQuery;
import com.shape.web.dto.JsonResult;

import java.util.List;

public interface EvaluateService {

    JsonResult insertEvaluate(Evaluate evaluate);

    JsonResult<List<Evaluate>> queryEvaluate(EvaluateQuery evaluateQuery);

    JsonResult revertEvaluate(Evaluate evaluate);
}
