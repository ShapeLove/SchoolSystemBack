package com.shape.dao;

import com.shape.entity.User;
import com.shape.query.UserQuery;

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

    /**
     * 更新用户信息 这个主要是根据id更新
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 同样是更新用户 这个主要是根据userName更新
     * @param user
     * @return
     */
    int updateUserByUserName(User user);
}
