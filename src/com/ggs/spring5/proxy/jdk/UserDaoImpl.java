package com.ggs.spring5.proxy.jdk;

/**
 * @author: Starbug
 * @date: 2020/6/6 19:38
 */
public class UserDaoImpl implements UserDao {
  @Override
  public int add(int a, int b) {
    System.out.println("add方法执行了....");
    return a + b;
  }

  @Override
  public String update(String id) {
    System.out.println("update方法执行了....");
    return id;
  }
}
