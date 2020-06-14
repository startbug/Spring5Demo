package com.ggs.webflux;

import com.ggs.webflux.handler.UserHandler;
import com.ggs.webflux.service.UserService;
import com.ggs.webflux.service.impl.NewUserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author: Starbug
 * @date: 2020/6/14 14:10
 */
public class Server {

  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.createReactorServer();
    System.out.println("已经开启了");
    System.in.read();
  }

  // 1.创建router对象
  public RouterFunction<ServerResponse> routingFunction() {
    // 创建handler对象
    UserService userService = new NewUserServiceImpl();
    UserHandler userHandler = new UserHandler(userService);
    // 设置路由
    return RouterFunctions.route(
            GET("/user/{id}").and(accept(APPLICATION_JSON)), userHandler::getById)
        .andRoute(GET("/users").and(accept(APPLICATION_JSON)), userHandler::getAll)
        .andRoute(POST("/insert/user").and(accept(APPLICATION_JSON)), userHandler::insertUser);
  }

  //2.创建服务器完成适配
  public void createReactorServer(){
    //路由和handler适配
    RouterFunction<ServerResponse> router = routingFunction();
    //HttpHandler完成http请求,存储http请求相关的信息
    HttpHandler httpHandler = toHttpHandler(router);
    //完成最终的适配
    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

    //创建服务器
    HttpServer httpServer = HttpServer.create();
    //传入适配器,一启动,就会启动服务器,完成适配,当访问的时候,通过路由分配到具体的访问路径
    httpServer.handle(adapter).bindNow();
  }
}
