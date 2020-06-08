package com.ggs.spring5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Starbug
 * @date: 2020/6/5 22:24
 */
@Repository("userDaoImpl1")
public class UserDaoImpl implements UserDao {

  @Override
  public void add() {
    System.out.println("dao add.........");
  }
}
