package com.shape.dao;

import com.shape.entity.Activity;

import java.util.List;


/**
 * 日常活动数据库接口
 */
public interface ActivityDao {
    /**
     * 插入日常活动信息
     * @param activity
     * @return
     */
    int insertActivity(Activity activity);

    /**
     * 查询日常活动信息
     * @return
     */
    List<Activity> queryActivity();
}
