package com.shape.web.service;

import com.shape.entity.Activity;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;

public interface ActivityService {

    JsonResult addActivity(Activity activity);

    JsonResult<PageResult<Activity>> queryActivity();
}
