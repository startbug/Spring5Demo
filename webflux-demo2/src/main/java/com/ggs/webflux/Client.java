package com.ggs.webflux;

import com.ggs.webflux.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author: Starbug
 * @date: 2020/6/14 14:45
 */
public class Client {
  public static void main(String[] args) {
    WebClient webClient = WebClient.create("http://127.0.0.1:4697");
    // 根据id查询
    Integer id = 1;
    User user =
        webClient
            .get()
            .uri("/user/{id}", id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(User.class)
            .block();

    System.out.println(user);

    // 查询所有
    Flux<User> users =
        webClient
            .get()
            .uri("/users")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(User.class);

    users.map(userInfo->userInfo.toString()).buffer().doOnNext(System.out::println).blockFirst();
  }
}
