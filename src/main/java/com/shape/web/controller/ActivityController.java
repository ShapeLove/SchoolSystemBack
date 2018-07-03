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

/**
 * 活动控制器
 */
@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 添加活动
     * @param activity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Activity activity) {
        /**
         * 1.首先获取activity 从前端传过来并经过Spring自己通过Json转换后的对象 并且整体trycatch 有错误就是JsonResult添加错误提示信息
         * 其他的都是这样的
         * 2.默认创建一个失败的JsonResult 等所有处理成功时在赋值一个成功的JsonResult
         * 3.从当前会话中获取用户信息
         * 4.判断用户角色是否为班主任 不是返回提示 如果是设置登录用户的班主任所在班级的Id 加上前台传过来的数据调用业务逻辑层进行业务处理
         */
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
