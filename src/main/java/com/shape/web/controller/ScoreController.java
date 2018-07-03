package com.shape.web.controller;

import com.shape.entity.CustomScore;
import com.shape.entity.Score;
import com.shape.query.ScoreQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.dto.PageResult;
import com.shape.web.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "list" , method = RequestMethod.POST)
    public JsonResult<PageResult<CustomScore>> list(@RequestBody ScoreQuery query) {
        return scoreService.query(query);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody Score score) {

        return scoreService.add(score);
    }
}
