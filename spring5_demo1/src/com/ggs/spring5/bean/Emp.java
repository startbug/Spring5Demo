package com.ggs.spring5.bean;

/**
 * @author: Starbug
 * @date: 2020/6/4 22:33
 * 员工类
 */
public class Emp {

  private String id;
  private String ename;
  //员工属于某个部门,使用对象的形式表示
  private Dept dept;

  public Dept getDept() {
    return dept;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public void setDept(Dept dept) {
    this.dept = dept;
  }

  public void printInfo() {
    System.out.println(id + "::" + ename + "::" + dept);
  }
}
