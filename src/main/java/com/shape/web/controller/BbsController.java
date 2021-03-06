package com.shape.web.controller;

import com.shape.entity.Bbs;
import com.shape.query.BbsQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.BbsService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    private BbsService bbsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Bbs bbs) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (!customUser.getRole().equals("student")) {
            jsonResult.setMessage("只有学生家长才能发布留言");
        }else {
            bbs.setStudentId(customUser.getUserName());
            jsonResult = bbsService.addBbs(bbs);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<List<Bbs>> query(@RequestBody BbsQuery query) {
        CustomUser user = WebContext.getUserFromSession();
        if (user.getRole().equals("teacher") || user.getRole().equals("master")) {
            query.setTeacherId(user.getUserName());
        }else if (user.getRole().equals("student")) {
            query.setStudentId(user.getUserName());
        }
        return bbsService.queryBbs(query);
    }

    @RequestMapping(value = "/revert",method = RequestMethod.POST)
    public JsonResult revert(@RequestBody Bbs bbs) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser.getRole().equals("master") || customUser.getRole().equals("teacher")) {
            jsonResult = bbsService.revertBbs(bbs);
        }else {
            jsonResult.setMessage("只有教师能够回复");
        }
        return jsonResult;
    }
}
