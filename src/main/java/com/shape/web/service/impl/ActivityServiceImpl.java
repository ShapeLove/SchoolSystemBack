package com.shape.web.service.impl;

import com.shape.dao.ActivityDao;
import com.shape.entity.Activity;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public JsonResult addActivity(Activity activity) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            activityDao.insertActivity(activity);
            jsonResult = JsonResult.successResult();
        } catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }

    @Override
    public JsonResult<PageResult<Activity>> queryActivity() {
        JsonResult<PageResult<Activity>> jsonResult = JsonResult.falseResult();
        try {
            List<Activity> activities = activityDao.queryActivity();
            PageResult<Activity> activityPageResult = new PageResult<>(1, 20, activities);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(activityPageResult);
        } catch (Exception e) {
            jsonResult.setMessage("系统异常");
            log.error("error:{}", e);
        }
        return jsonResult;
    }
}
