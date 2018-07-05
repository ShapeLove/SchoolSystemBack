package com.shape.dao;

import com.shape.entity.Homework;
import com.shape.query.HomeworkQuery;

import java.util.List;

/**
 * 家庭作业数据库接口
 */
public interface HomeworkDao {
    int insertHomework(Homework homework);

    /**
     * 可以根据教工号、学号和班级查询作业
     * @param query
     * @return
     */
    List<Homework> queryHomework(HomeworkQuery query);

    /**
     * 根据作业id更新信息 主要更新教师评价和家长评价
     * @param homework
     * @return
     */
    int updateHomework(Homework homework);

    int deleteHomework(List<Integer> homeworkIds);
}
