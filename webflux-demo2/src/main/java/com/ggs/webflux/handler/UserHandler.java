package com.ggs.webflux.handler;

import com.ggs.webflux.entity.User;
import com.ggs.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author: Starbug
 * @date: 2020/6/13 23:30
 */
public class UserHandler {

  private final UserService userService;

  public UserHandler(UserService userService) {
    this.userService = userService;
  }

  // 根据id查询
  public Mono<ServerResponse> getById(ServerRequest request) {
    // 从路径中获取id值
    int id = Integer.parseInt(request.pathVariable("id"));
    // 空值处理,构建一个空的流
    Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    // 调用Service方法获取数据
    Mono<User> userMono = userService.findById(id);
    // 把userMono通过flatMap方法将多个流(这里只有一个流Mono<User>)合并成一个流再输出到一个流中(Mono<ServerResponse>)
    return userMono
        .flatMap(
            user -> {
              // user值就是userMono
              return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(user));
            })
        .switchIfEmpty(notFound);
  }

  //查询所有
  public Mono<ServerResponse> getAll(ServerRequest request){
    //调用Service得到结果
    Flux<User> usersListFlux = userService.findAll();

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(usersListFlux,User.class);
  }

  //添加
  public Mono<ServerResponse> insertUser(ServerRequest request){
    Mono<User> userMono = request.bodyToMono(User.class);
    return ServerResponse.ok().build(userService.insertUser(userMono));
  }
}
