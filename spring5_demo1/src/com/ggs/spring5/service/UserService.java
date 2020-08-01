package com.ggs.spring5.service;

import com.ggs.spring5.dao.UserDao;

/**
 * @author: Starbug
 * @date: 2020/6/4 18:58
 */
public class UserService {

  //创建UserDao类型属性, 生成set方法
  private UserDao userDao;

  public void add() {
    System.out.println("service add+++++++++++++++++++");
    userDao.update();
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
