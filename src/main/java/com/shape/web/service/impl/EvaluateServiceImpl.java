package com.shape.web.service.impl;

import com.shape.dao.EvaluateDao;
import com.shape.dao.StudentDao;
import com.shape.entity.Evaluate;
import com.shape.entity.Student;
import com.shape.query.EvaluateQuery;
import com.shape.query.StudentQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.EvaluateService;
import com.shape.web.util.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public JsonResult insertEvaluate(Evaluate evaluate) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            StudentQuery studentQuery = new StudentQuery();
            studentQuery.setStudentId(evaluate.getStudentId());
            List<Student> studentList = studentDao.queryStudent(studentQuery);
            if (CollectionUtils.isEmpty(studentList)) {
                jsonResult.setMessage("没有该学生信息");
            }else {
                if (WebContext.getUserFromSession().getMasterClassId() != studentList.get(0).getClassId()) {
                    jsonResult.setMessage("不能评价不是本班的学生");
                }else {
                    evaluateDao.insertEvaluate(evaluate);
                    jsonResult = JsonResult.successResult();
                }
            }
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
