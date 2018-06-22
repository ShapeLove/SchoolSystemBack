package com.shape.web.service;

import com.shape.entity.Advice;
import com.shape.web.dto.JsonResult;

import java.util.List;

public interface AdviceService {

    JsonResult addAdvice(Advice advice);

    JsonResult<List<Advice>> queryAdvice();
}
