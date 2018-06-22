package com.shape.web.service.impl;

import com.shape.dao.AdviceDao;
import com.shape.entity.Advice;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.AdviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceDao adviceDao;

    @Override
    public JsonResult addAdvice(Advice advice) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            adviceDao.insertAdvice(advice);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<List<Advice>> queryAdvice() {
        JsonResult<List<Advice>> jsonResult = JsonResult.falseResult();
        try {
            List<Advice> adviceList = adviceDao.queryAdvice();
            jsonResult = JsonResult.successResult();
            jsonResult.setData(adviceList);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
