package com.shape.web.controller;

import com.shape.entity.Activity;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.ActivityService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Activity activity) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            CustomUser customUser = WebContext.getUserFromSession();
            if (customUser.getRole().equals("master")) {
                activity.setClassId(customUser.getMasterClassId());
                jsonResult = activityService.addActivity(activity);
            }else {
                jsonResult.setMessage("只有班主任才能发布活动");
            }
        }catch (Exception e) {
            jsonResult.setMessage("系統異常");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<PageResult<Activity>> query() {
        return activityService.queryActivity();
    }

}
