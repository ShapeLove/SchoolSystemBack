package com.shape.web.service.impl;

import com.shape.dao.ClassDao;
import com.shape.dao.ScoreDao;
import com.shape.dao.StudentDao;
import com.shape.entity.Class;
import com.shape.entity.CustomScore;
import com.shape.entity.Score;
import com.shape.entity.Student;
import com.shape.query.ClassQuery;
import com.shape.query.ScoreQuery;
import com.shape.query.StudentQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.ScoreService;
import com.shape.web.util.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional
    public JsonResult add(Score score) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            CustomUser user = WebContext.getUserFromSession();
            // 先查是否是班主任
            ClassQuery query = new ClassQuery();
            query.setMasterId(user.getUserName());
            List<Class> classList = classDao.queryClass(query);
            if (CollectionUtils.isEmpty(classList)) {
                jsonResult.setMessage("班主任才能够插入学生成绩");
            } else {
                int classId = classList.get(0).getId();
                StudentQuery studentQuery = new StudentQuery();
                studentQuery.setStudentId(score.getStudentId());
                List<Student> studentList = studentDao.queryStudent(studentQuery);
                if (CollectionUtils.isEmpty(studentList)) {
                    jsonResult.setMessage("没有此学生信息");
                }else {
                    //判断是否为本班学生
                    if (studentList.get(0).getClassId().equals(classId)) {
                        scoreDao.insertScore(score);
                        jsonResult = JsonResult.successResult();
                    } else {
                        jsonResult.setMessage("不是本班学生不可插入成绩");
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<PageResult<CustomScore>> query(ScoreQuery query) {
        JsonResult<PageResult<CustomScore>> jsonResult = JsonResult.falseResult();
        try {
                List<CustomScore> scoreList = scoreDao.queryScore(query);
                PageResult<CustomScore> scorePageResult = new PageResult<>(query.getPageIndex(),query.getPageSize(),scoreList);
                jsonResult = JsonResult.successResult();
                jsonResult.setData(scorePageResult);
        } catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
