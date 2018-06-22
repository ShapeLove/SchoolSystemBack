package com.shape.web.controller;


import com.shape.entity.Advice;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.AdviceService;
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
        return adviceService.addAdvice(advice);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult<List<Advice>> query() {
        return adviceService.queryAdvice();
    }
}
