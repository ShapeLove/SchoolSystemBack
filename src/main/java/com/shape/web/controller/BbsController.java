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
        return bbsService.addBbs(bbs);
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
        return bbsService.revertBbs(bbs);
    }
}
