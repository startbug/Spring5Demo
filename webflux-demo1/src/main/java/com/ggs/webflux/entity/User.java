package com.ggs.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Starbug
 * @date: 2020/6/13 22:10
 */
@Data
@AllArgsConstructor
public class User {
  private Integer id;
  private Integer age;
  private String username;
}
