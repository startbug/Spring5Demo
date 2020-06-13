package com.ggs.webflux.service.impl;

import com.ggs.webflux.entity.User;
import com.ggs.webflux.mapper.UserMapper;
import com.ggs.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/13 22:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mono<User> findById(Integer id) {
        User user=userMapper.findById(id);
        return Mono.just(user);
    }

    @Override
    public Flux<User> findAll() {
        List<User> users=userMapper.findAll();
        Flux<User> userListFlux = Flux.fromIterable(users);
        return userListFlux;
    }

    @Override
    public Mono<Void> insertUser(Mono<User> userMono) {
        Mono<Void> voidMono = userMono
                .doOnNext(
                        user -> {
                            Integer effectRow = userMapper.insertUser(user);
                            System.out.println(effectRow);
                        })
                .thenEmpty(Mono.empty());// 结束之后传入终止信号
        return voidMono;
    }
}
