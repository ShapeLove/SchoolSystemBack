package com.shape.web.service.impl;

import com.shape.dao.ClassTeacherDao;
import com.shape.dao.HomeworkDao;
import com.shape.dao.StudentDao;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Homework;
import com.shape.entity.Student;
import com.shape.query.ClassTeacherQuery;
import com.shape.query.HomeworkQuery;
import com.shape.query.StudentQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.HomeworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
@Slf4j
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private ClassTeacherDao classTeacherDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional
    public JsonResult add(Homework homework) {
        JsonResult jsonResult = JsonResult.falseResult();
        try{
            ClassTeacherQuery classTeacherQuery = new ClassTeacherQuery();
            classTeacherQuery.setTeacherId(homework.getTeacherId());
            List<ClassTeacher> classTeacherList = classTeacherDao.queryClassTeacher(classTeacherQuery);
            if (CollectionUtils.isEmpty(classTeacherList)) {
                jsonResult.setMessage("教师还没被分配班级，无法布置作业");
            }else {
                int classId = homework.getClassId();
                boolean flag = false;
                for (ClassTeacher classTeacher : classTeacherList) {
                    if (classTeacher.getClassId().equals(classId)) {
                        flag = true;
                    }
                }
                if (flag) {
                    StudentQuery studentQuery = new StudentQuery();
                    studentQuery.setClassId(classId);
                    List<Student> studentList = studentDao.queryStudent(studentQuery);
                    if (CollectionUtils.isEmpty(studentList)) {
                        jsonResult.setMessage("该班没有学生");
                    }else {
                        Homework temp = homework;
                        temp.setTeacherId(homework.getTeacherId());
                        for (Student student : studentList) {
                            temp.setStudentId(student.getId());
                            homeworkDao.insertHomework(temp);
                        }
                        jsonResult = JsonResult.successResult();
                    }
                }else {
                    jsonResult.setMessage("教师只能发布自己教班级的作业");
                }
            }
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<PageResult<Homework>> quest(HomeworkQuery query) {
        JsonResult<PageResult<Homework>> jsonResult = JsonResult.falseResult();
        try {
            List<Homework> homeworkList = homeworkDao.queryHomework(query);
            PageResult<Homework> scorePageResult = new PageResult<>(query.getPageIndex(),query.getPageSize(),homeworkList);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(scorePageResult);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult revertHomework(Homework homework) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            homeworkDao.updateHomework(homework);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
        }
        return jsonResult;
    }

    @Override
    public JsonResult deleteHomework(List<Integer> homeworkIds) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            homeworkDao.deleteHomework(homeworkIds);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
        }
        return jsonResult;
    }

}
