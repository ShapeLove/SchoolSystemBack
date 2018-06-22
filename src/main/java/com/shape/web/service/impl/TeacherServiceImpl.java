package com.shape.web.service.impl;

import com.shape.dao.ClassDao;
import com.shape.dao.ClassTeacherDao;
import com.shape.dao.TeacherDao;
import com.shape.dao.UserDao;
import com.shape.entity.Class;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Teacher;
import com.shape.entity.User;
import com.shape.query.ClassQuery;
import com.shape.query.ClassTeacherQuery;
import com.shape.query.TeacherQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.TeacherService;
import com.shape.web.util.Contant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ClassTeacherDao classTeacherDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult add(Teacher teacher) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            TeacherQuery teacherQuery = new TeacherQuery();
            teacherQuery.setTeacherId(teacher.getId());
            List<Teacher> teacherList = teacherDao.queryTeacher(teacherQuery);
            if (teacherList != null && teacherList.size() > 0) {
                jsonResult.setMessage("教工号已存在");
            }else {
                teacherDao.insertTeacher(teacher);
                User user = new User();
                user.setUserName(teacher.getId());
                user.setUserPassword(Contant.USER_DEFAULT_PASSWORD);
                user.setRole("teacher");
                userDao.insertUser(user);
                jsonResult = JsonResult.successResult();
            }
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<PageResult<Teacher>> query(TeacherQuery query) {
        JsonResult<PageResult<Teacher>> jsonResult = JsonResult.falseResult();
        try {
            List<Teacher> teacherList = teacherDao.queryTeacher(query);
            PageResult<Teacher> pageResult = new PageResult<>(query.getPageIndex(), query.getPageSize(), teacherList);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(pageResult);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult setMaster(Class cla) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            TeacherQuery teacherQuery = new TeacherQuery();
            teacherQuery.setTeacherId(cla.getMasterId());
            List<Teacher> teacherList = teacherDao.queryTeacher(teacherQuery);
            if (CollectionUtils.isEmpty(teacherList)) {
                jsonResult.setMessage("没有此教师信息");
            }else {
                ClassQuery classQuery = new ClassQuery();
                classQuery.setMasterId(cla.getMasterId());
                List<Class> classList = classDao.queryClass(classQuery);
                if (!CollectionUtils.isEmpty(classList)) {
                    jsonResult.setMessage("他已经是班主任了");
                }else {
                    classDao.updateClass(cla);
                    ClassTeacher classTeacher = new ClassTeacher();
                    classTeacher.setClassId(cla.getId());
                    classTeacher.setTeacherId(cla.getMasterId());
                    classTeacherDao.insertClassTeacher(classTeacher);
                    User user = new User();
                    user.setRole("master");
                    user.setUserName(cla.getMasterId());
                    userDao.updateUserByUserName(user);
                }
                jsonResult = JsonResult.successResult();
            }
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult setTeacherToClass(ClassTeacher classTeacher) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            ClassTeacherQuery classTeacherQuery = new ClassTeacherQuery();
            classTeacherQuery.setTeacherId(classTeacher.getTeacherId());
            classTeacherQuery.setClassId(classTeacher.getClassId());
            List<ClassTeacher> classTeachers = classTeacherDao.queryClassTeacher(classTeacherQuery);
            if (CollectionUtils.isEmpty(classTeachers)) {
                classTeacherDao.insertClassTeacher(classTeacher);
                jsonResult = JsonResult.successResult();
            }else {
                jsonResult.setMessage("记录已经存在");
            }
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
