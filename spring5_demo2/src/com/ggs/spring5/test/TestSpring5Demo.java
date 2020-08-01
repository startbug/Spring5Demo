package com.ggs.spring5.test;

import com.ggs.spring5.autowired.Emp;
import com.ggs.spring5.bean.Orders;
import com.ggs.spring5.collection.type.Book;
import com.ggs.spring5.collection.type.Course;
import com.ggs.spring5.collection.type.Stu;
import com.ggs.spring5.factorybean.MyBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Starbug
 * @date: 2020/6/4 23:02
 */
public class TestSpring5Demo {

  @Test
  public void testCollection() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    Stu stu = context.getBean("stu", Stu.class);

    stu.test();
  }

  @Test
  public void testBook() {
    BeanFactory context = new ClassPathXmlApplicationContext("bean2.xml");
    Book book = context.getBean("book", Book.class);
    book.test();
  }

  @Test
  public void testFactoryBean() {
    BeanFactory context = new ClassPathXmlApplicationContext("bean3.xml");
    Course course = context.getBean("myBean", Course.class);
    System.out.println(course);
  }

  @Test
  public void testBookType() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
    Book book1 = context.getBean("book", Book.class);
    Book book2 = context.getBean("book", Book.class);
    System.out.println(book1);
    System.out.println(book2);
  }

  @Test
  public void testOrders() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
    Orders orders = context.getBean("orders", Orders.class);
    System.out.println("第四步: 实例对象使用ing");
    System.out.println(orders);

    ((ClassPathXmlApplicationContext) context).close();
  }
  @Test
  public void testAutowired() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
    Emp emp = context.getBean("emp", Emp.class);
    System.out.println(emp);

  }
}
