package com.ggs.spring5.testdemo;

import com.ggs.spring5.Book;
import com.ggs.spring5.Orders;
import com.ggs.spring5.User;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Starbug
 * @date: 2020/6/2 22:58
 */
public class TestSpring5 {

  @Test
  public void testAdd() {
    // 1.加载spring配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

    // 2.获取配置创建对象
    User user = context.getBean("user", User.class);

    System.out.println(user);
    user.add();
  }
  
  @Test
  public void bookTest(){
    //1.加载Spring配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

    //2.获取配置创建的对象
    Book book = context.getBean("book", Book.class);

    System.out.println(book);
  }

  @Test
  public void ordersTest(){
    ApplicationContext context=new ClassPathXmlApplicationContext("bean1.xml");
    Orders orders = context.getBean("orders", Orders.class);

    System.out.println(orders);
    System.out.println(orders.toString());
  }

}
