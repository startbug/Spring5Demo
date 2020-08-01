package com.ggs.spring5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author: Starbug
 * @date: 2020/6/10 22:19
 */
@Repository
public class UserDaoImpl implements UserDao {

  @Autowired private JdbcTemplate jdbcTemplate;

  // lucy转账100给marry
  // 加前
  @Override
  public void decreaseMoney() {
    String sql = "update t_account set money=money-? where username=?";
    jdbcTemplate.update(sql, 100, "lucy");
  }
  // 捡钱
  @Override
  public void increaseMoney() {
    String sql = "update t_account set money=money+? where username=?";
    jdbcTemplate.update(sql, 100, "marry");
  }
}
