package com.shape.web.controller;


import com.shape.entity.Advice;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.AdviceService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Advice advice) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (!customUser.getRole().equals("master")) {
            jsonResult.setMessage("只有班主任才能发布通知");
        }else {
            jsonResult = adviceService.addAdvice(advice);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<List<Advice>> query() {
        return adviceService.queryAdvice();
    }
}
