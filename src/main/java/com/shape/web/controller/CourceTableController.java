package com.shape.web.controller;

import com.shape.entity.CourceTable;
import com.shape.query.CourceTableQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.CourseTableService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courcetable")
public class CourceTableController {

    @Autowired
    private CourseTableService courseTableService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody List<CourceTable> courceTable) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("master")) {
            for (CourceTable table : courceTable) {
                table.setClassId(customUser.getMasterClassId());
            }
            jsonResult = courseTableService.insertCourseTable(courceTable);
        } else {
            jsonResult.setMessage("只有班主任才能添加课程表");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<List<CourceTable>> query(@RequestBody CourceTableQuery query) {
        return courseTableService.queryCouseTable(query);
    }

}
