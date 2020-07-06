package com.neo.mapper;

import com.neo.model.User;

import java.util.List;

public interface UserMapper {

    List<User> getAll();

    /**
     * 分页查询用户
     * @return
     */
    List<User> selectPage();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}