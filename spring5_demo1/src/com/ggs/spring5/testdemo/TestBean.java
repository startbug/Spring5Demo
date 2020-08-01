package com.ggs.spring5.testdemo;

import com.ggs.spring5.bean.Emp;
import com.ggs.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Starbug
 * @date: 2020/6/4 19:09
 */
public class TestBean {

  @Test
  public void testAdd() {
    // 1.加载配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");

    // 2.获取配置文件创建的对象
    UserService userService = context.getBean("userService", UserService.class);

    userService.add();
  }

  @Test
  public void testWork() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");

    Emp emp = context.getBean("emp", Emp.class);

    System.out.println(emp);
    emp.printInfo();
  }
}
