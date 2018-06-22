package com.shape.dao;

import com.shape.entity.Bbs;
import com.shape.query.BbsQuery;

import java.util.List;

/**
 * 留言板数据库接口
 */
public interface BbsDao {
    /**
     * 插入留言
     *
     * @param bbs
     * @return
     */
    int insertBbs(Bbs bbs);

    /**
     * 查看留言
     * @return
     */
    List<Bbs> queryBbs(BbsQuery query);
    int updateBbs(Bbs bbs);
}
