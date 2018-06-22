package com.shape.web.service;

import com.shape.entity.CourceTable;
import com.shape.query.CourceTableQuery;
import com.shape.web.dto.JsonResult;

import java.util.List;

public interface CourseTableService {
    JsonResult insertCourseTable(List<CourceTable> courceTableList);

    JsonResult<List<CourceTable>> queryCouseTable(CourceTableQuery query);
}
