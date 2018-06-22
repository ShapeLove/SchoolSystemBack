package com.shape.dao;

import com.shape.entity.User;
import com.shape.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据库接口
 */
public interface UserDao {
    /**
     * 插入用户信息
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 查询用户信息
     * @param query 支持按照用户名、角色查询
     * @return
     */
    List<User> queryUser(UserQuery query);

    int updateUser(User user);
    int updateUserByUserName(User user);
}
