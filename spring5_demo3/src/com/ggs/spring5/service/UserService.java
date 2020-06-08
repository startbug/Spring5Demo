package com.ggs.spring5.service;

import com.ggs.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Starbug
 * @date: 2020/6/5 22:04
 */
@Service // <bean id="userService" class="..></bean>
public class UserService {

  @Value(value = "abc")
  private String name;

  // 定义dao类型属性
  // 不需要添加set方法
  // 添加注入属性注解
//  @Autowired //根据类型进行注入
//  @Qualifier("userDaoImpl1")
//  private UserDao userDao;

//  @Resource
  @Resource(name = "userDaoImpl1")
  private UserDao userDao;

  public void add() {
    System.out.println("add...."+name);
    userDao.add();
  }
}
