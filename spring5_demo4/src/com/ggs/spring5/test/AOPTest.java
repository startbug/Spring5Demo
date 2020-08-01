package com.ggs.spring5.test;

import com.ggs.spring5.aopanno.User;
import com.ggs.spring5.aopxml.Book;
import com.ggs.spring5.config.ConfigAop;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Starbug
 * @date: 2020/6/6 23:47
 *     asm-3.3.1.jar cglib-2.2.2.jar
 *     com.springsource.net.sf.cglib-2.2.0.jar
 *     com.springsource.org.aopalliance-1.0.0.jar
 *     com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
 *     commons-logging-1.1.1.jar
 *     druid-1.1.22.jar spring-aop-5.2.6.RELEASE.jar
 *     spring-aspects-5.2.6.RELEASE.jar
 *     spring-beans-5.2.6.RELEASE.jar spring-context-5.2.6.RELEASE.jar
 *     spring-core-5.2.6.RELEASE.jar
 *     spring-expression-5.2.6.RELEASE.jar
 */
public class AOPTest {

  @Test
  public void testAopAnno() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    User user = context.getBean("user", User.class);
    user.add();
  }

  @Test
  public void testAopAnnoWithConfigAop() {
    ApplicationContext context = new AnnotationConfigApplicationContext(ConfigAop.class);
    User user = context.getBean("user", User.class);
    user.add();
  }

  @Test
  public void testAopxml() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
    Book book = context.getBean("book", Book.class);
    book.addBook();
  }
}
