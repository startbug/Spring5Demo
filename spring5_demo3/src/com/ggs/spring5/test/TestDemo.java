package com.ggs.spring5.test;

import com.ggs.spring5.config.SpringConfig;
import com.ggs.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Starbug
 * @date: 2020/6/5 22:01
 */
public class TestDemo {

  @Test
  public void testUserService() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    UserService userService = context.getBean("userService", UserService.class);
    System.out.println(userService);
    userService.add();
  }

  @Test
  public void testServicec2() {
    // 加载配置类,代替加载xml配置文件
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    UserService userService = context.getBean("userService", UserService.class);
    System.out.println(userService);
    userService.add();
  }
}
