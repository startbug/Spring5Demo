package com.ggs.spring5.test;

import com.ggs.spring5.entity.User;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author: Starbug
 * @date: 2020/6/11 23:33
 */
public class FunctionalTest {

  // 函数式风格创建对象,交给Spring进行管理
  @Test
  public void testGenericApplicationContext() {
    // 1.创建GenericApplicationContext对象
    GenericApplicationContext context = new GenericApplicationContext();
    // 2.调用context的方法对象注册
    context.refresh(); // 清空容器
    context.registerBean("user11",User.class, () -> new User());
    // 3.获取在spring注册的对象
    User user = (User) context.getBean("user11");
//    User user = (User) context.getBean("com.ggs.spring5.entity.User");
    System.out.println(user);
  }
}
