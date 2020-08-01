package com.ggs.webflux.service.impl;

import com.ggs.webflux.entity.User;
import com.ggs.webflux.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Starbug
 * @date: 2020/6/14 14:34
 */
public class NewUserServiceImpl implements UserService { // 创建map集合存储数据
  private final Map<Integer, User> users = new HashMap<>();

  public NewUserServiceImpl() {
    this.users.put(1, new User(1, 20, "nan"));
    this.users.put(2, new User(2, 30, "sdf"));
    this.users.put(3, new User(3, 33, "aaaaa"));
  }

  // 根据id查询
  @Override
  public Mono<User> findById(Integer id) {
    return Mono.justOrEmpty(this.users.get(id));
  }
  // 查询多个用户
  @Override
  public Flux<User> findAll() {
    return Flux.fromIterable(this.users.values());
  }
  // 添加用户
  @Override
  public Mono<Void> insertUser(Mono<User> userMono) {
    return userMono
        .doOnNext(
            person -> { // 向map集合里面放值
              int id = users.size() + 1;
              users.put(id, person);
            })
        .thenEmpty(Mono.empty());
  }
}
