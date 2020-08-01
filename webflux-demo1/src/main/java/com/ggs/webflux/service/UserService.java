package com.ggs.webflux.service;

import com.ggs.webflux.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Starbug
 * @date: 2020/6/13 22:14
 */
public interface UserService {

    //根据id查询用户
    Mono<User> findById(Integer id);

    //查询所有用户
    Flux<User> findAll();

    //添加用户信息
    Mono<Void> insertUser(Mono<User> user);
}
