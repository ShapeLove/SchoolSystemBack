package com.shape.web.service;

import com.shape.entity.Bbs;
import com.shape.query.BbsQuery;
import com.shape.web.dto.JsonResult;

import java.util.List;

public interface BbsService {

    JsonResult addBbs(Bbs bbs);
    JsonResult<List<Bbs>> queryBbs(BbsQuery bbsQuery);
    JsonResult revertBbs(Bbs bbs);
}
