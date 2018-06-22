package com.shape.web.service.impl;

import com.shape.dao.EvaluateDao;
import com.shape.entity.Evaluate;
import com.shape.query.EvaluateQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.EvaluateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    @Override
    public JsonResult insertEvaluate(Evaluate evaluate) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            evaluateDao.insertEvaluate(evaluate);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<List<Evaluate>> queryEvaluate(EvaluateQuery evaluateQuery) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            List<Evaluate> evaluateList = evaluateDao.queryEvaluate(evaluateQuery);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(evaluateList);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult revertEvaluate(Evaluate evaluate) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            evaluateDao.updateEvaluate(evaluate);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
