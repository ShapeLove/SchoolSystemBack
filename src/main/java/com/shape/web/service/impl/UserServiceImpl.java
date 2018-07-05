package com.shape.web.service.impl;

import com.shape.dao.ClassDao;
import com.shape.dao.ClassTeacherDao;
import com.shape.dao.StudentDao;
import com.shape.dao.UserDao;
import com.shape.entity.Class;
import com.shape.entity.ClassTeacher;
import com.shape.entity.Student;
import com.shape.entity.User;
import com.shape.query.ClassQuery;
import com.shape.query.ClassTeacherQuery;
import com.shape.query.StudentQuery;
import com.shape.query.UserQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.UserService;
import com.shape.web.util.Contant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClassTeacherDao classTeacherDao;

    @Autowired
    private ClassDao classDao;

    @Override
    public JsonResult login(UserQuery user, HttpServletRequest request) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            UserQuery query = new UserQuery();
            query.setUserName(user.getUserName());
            List<User> userList = userDao.queryUser(query);
            if (userList == null || userList.size() == 0) {
                jsonResult.setMessage("用户名不存在");
            }else {
                User currentUser = userList.get(0);
                if (currentUser.getUserPassword().equals(user.getUserPassword())) {
                    CustomUser customUser = new CustomUser();
                    customUser.setId(currentUser.getId());
                    customUser.setUserName(currentUser.getUserName());
                    customUser.setUserPassword(currentUser.getUserPassword());
                    customUser.setRole(currentUser.getRole());
                    if (customUser.getRole().equals("teacher") || customUser.getRole().equals("master")) {
                        ClassTeacherQuery classTeacherQuery = new ClassTeacherQuery();
                        classTeacherQuery.setTeacherId(customUser.getUserName());
                        List<ClassTeacher> classTeacherList = classTeacherDao.queryClassTeacher(classTeacherQuery);
                        if (!CollectionUtils.isEmpty(classTeacherList)) {
                            List<Integer> classId = new ArrayList<>();
                            for (ClassTeacher classTeacher : classTeacherList) {
                                classId.add(classTeacher.getClassId());
                            }
                            customUser.setTeachClassIdList(classId);
                        }
                    }
                    if (customUser.getRole().equals("master")) {
                        ClassQuery classQuery = new ClassQuery();
                        List<Class> classList = classDao.queryClass(classQuery);
                        customUser.setMasterClassId(classList.get(0).getId());
                    }
                    if (customUser.getRole().equals("student")) {
                        StudentQuery studentQuery = new StudentQuery();
                        studentQuery.setStudentId(customUser.getUserName());
                        List<Student> studentList = studentDao.queryStudent(studentQuery);
                        customUser.setClassId(studentList.get(0).getClassId());
                    }
                    request.getSession().setAttribute(Contant.SESSION_USER_KEY, customUser);
                    jsonResult = JsonResult.successResult();
                }else {
                    jsonResult.setMessage("用户名密码错误");
                }
            }
        }catch (NullPointerException e) {
            jsonResult.setMessage("数据为空");
            log.error("error:{}", e);
        } catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult loginOut(HttpServletRequest request) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            request.getSession().removeAttribute(Contant.SESSION_USER_KEY);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult resetPassword(User user) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            userDao.updateUser(user);
            jsonResult = JsonResult.successResult();
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

}
