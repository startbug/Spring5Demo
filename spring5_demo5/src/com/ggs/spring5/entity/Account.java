package com.ggs.spring5.entity;

/**
 * @author: Starbug
 * @date: 2020/6/10 22:14
 */
public class Account {
  private String id;
  private String username;
  private Integer money;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getMoney() {
    return money;
  }

  public void setMoney(Integer money) {
    this.money = money;
  }
}
