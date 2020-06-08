package com.ggs.spring5.aopanno;

import org.springframework.stereotype.Component;

/**
 * @author: Starbug
 * @date: 2020/6/6 23:14
 */
@Component
public class User {
  public void add() {
//    int i=10/0;
    System.out.println("add....");
  }
}
