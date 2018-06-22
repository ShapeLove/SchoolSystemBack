package com.shape.web.controller;

import com.shape.entity.Evaluate;
import com.shape.query.EvaluateQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.EvaluateService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @RequestMapping(value = "/add", method =RequestMethod.POST)
    public JsonResult add(@RequestBody Evaluate evaluate) {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser user = WebContext.getUserFromSession();
        if (user.getRole().equals("master")) {
            evaluate.setTeacherId(user.getUserName());
            jsonResult = evaluateService.insertEvaluate(evaluate);
        }else {
            jsonResult.setMessage("只有班主任才能发布月评价");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<List<Evaluate>> query(@RequestBody EvaluateQuery evaluateQuery) {
        CustomUser user = WebContext.getUserFromSession();
        if (user.getRole().equals("master")) {
            evaluateQuery.setTeacherId(user.getUserName());
        }else if (user.getRole().equals("student")) {
            evaluateQuery.setStudentId(user.getUserName());
        }
        return evaluateService.queryEvaluate(evaluateQuery);
    }

    @RequestMapping(value = "/revert", method = RequestMethod.POST)
    public JsonResult revert(@RequestBody Evaluate evaluate) {
        return evaluateService.revertEvaluate(evaluate);
    }

}
