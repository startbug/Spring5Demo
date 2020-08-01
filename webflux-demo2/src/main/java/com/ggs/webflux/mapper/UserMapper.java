package com.ggs.webflux.mapper;

import com.ggs.webflux.entity.User;

import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/13 22:24
 */
public interface UserMapper {
    
    User findById(Integer id);

    List<User> findAll();

    Integer insertUser(User user);
}
