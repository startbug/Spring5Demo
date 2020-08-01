package com.ggs.spring5.bean;

/**
 * @author: Starbug
 * @date: 2020/6/4 22:33
 * 部门类
 */
public class Dept {

  private String dname;

  public void setDname(String dname) {
    this.dname = dname;
  }

  @Override
  public String toString() {
    return "Dept{" + "dname='" + dname + '\'' + '}';
  }
}
