package com.ggs.spring5;

/**
 * @author: Starbug
 * @date: 2020/6/2 22:48
 */
public class User {

  public User(){
    System.out.println("ApplicationContext才会在运行时创建,BeanFactory在用的时候才创建");
  }

  public void add() {
    System.out.println("add......");
  }

}
