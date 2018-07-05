package com.shape.web.service.impl;

import com.shape.dao.ClassTeacherDao;
import com.shape.dao.CourceTableDao;
import com.shape.entity.CourceTable;
import com.shape.query.CourceTableQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.CourseTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class CourceTableServiceImpl implements CourseTableService {

    @Autowired
    private CourceTableDao courceTableDao;

    @Autowired
    private ClassTeacherDao classTeacherDao;

    @Override
    @Transactional
    public JsonResult insertCourseTable(List<CourceTable> courceTableList) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            for (CourceTable courceTable : courceTableList) {
                CourceTableQuery courceTableQuery = new CourceTableQuery();
                courceTableQuery.setClassId(courceTable.getClassId());
                courceTableQuery.setJie(courceTable.getJie());
                courceTableQuery.setWeek(courceTable.getWeek());
                List<CourceTable> courceTableLists = courceTableDao.queryCourceTableByWeekJie(courceTableQuery);
                if (CollectionUtils.isEmpty(courceTableLists)) {
                    courceTableDao.insertCourceTable(courceTable);
                }else {
                    courceTableDao.updateCourceTable(courceTable);
                }
            }
            jsonResult = JsonResult.successResult();

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<List<CourceTable>> queryCouseTable(CourceTableQuery query) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            List<CourceTable> scoreList = courceTableDao.queryCourceTable(query);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(scoreList);
        } catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
