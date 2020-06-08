package com.ggs.spring5.collection.type;

import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/4 23:14
 */
public class Book {

  private List<String> list;


  public void setList(List<String> list) {
    this.list = list;
  }

  public void test() {
    System.out.println(list);
  }
}
