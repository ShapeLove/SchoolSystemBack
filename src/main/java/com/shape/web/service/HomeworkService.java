package com.shape.web.service;

import com.shape.entity.Homework;
import com.shape.query.HomeworkQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;

import java.util.List;

public interface HomeworkService {

    JsonResult add(Homework homework);

    JsonResult<PageResult<Homework>> quest(HomeworkQuery query);

    JsonResult revertHomework(Homework homework);

    JsonResult deleteHomework(List<Integer>homeworkIds);
}
