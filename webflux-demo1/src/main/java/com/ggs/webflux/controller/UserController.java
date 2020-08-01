package com.ggs.webflux.controller;

import com.ggs.webflux.entity.User;
import com.ggs.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Starbug
 * @date: 2020/6/13 22:35
 */
@RestController
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/user/{id}")
  public Mono<User> findById(@PathVariable Integer id) {
    return userService.findById(id);
  }

  @GetMapping("/user")
  public Flux<User> findAll() {
    return userService.findAll();
  }

  @PostMapping("/insert/user")
  public Mono<Void> insertUser(@RequestBody User user) {
    Mono<User> userMono = Mono.just(user);
    return userService.insertUser(userMono);
  }
}
