package com.ggs.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Starbug
 * @date: 2020/6/13 22:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Integer id;
  private Integer age;
  private String username;
}
