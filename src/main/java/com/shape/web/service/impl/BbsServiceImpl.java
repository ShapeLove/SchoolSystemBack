package com.shape.web.service.impl;

import com.shape.dao.BbsDao;
import com.shape.entity.Bbs;
import com.shape.query.BbsQuery;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.BbsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BbsServiceImpl implements BbsService {

    @Autowired
    private BbsDao bbsDao;

    @Override
    public JsonResult addBbs(Bbs bbs) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            bbsDao.insertBbs(bbs);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
        }
        return jsonResult;
    }

    @Override
    public JsonResult<List<Bbs>> queryBbs(BbsQuery bbsQuery) {
        JsonResult<List<Bbs>> jsonResult = JsonResult.falseResult();
        try {
            List<Bbs> bbsList = bbsDao.queryBbs(bbsQuery);
            jsonResult = JsonResult.successResult();
            jsonResult.setData(bbsList);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
        }
        return jsonResult;
    }

    @Override
    public JsonResult revertBbs(Bbs bbs) {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            bbsDao.updateBbs(bbs);
        }catch (Exception e) {
            jsonResult.setMessage("系統异常");
        }
        return jsonResult;
    }
}
