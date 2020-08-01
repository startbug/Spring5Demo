package com.ggs.spring5.dao;

import com.ggs.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/8 23:39
 */
@Repository
public class BookDaoImpl implements BookDao {
  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public void add(Book book) {
    String sql = "insert into t_book(user_id,username,ustatus) values(?,?,?)";
    Object[] args = {book.getUserId(), book.getUsername(), book.getUstatus()};
    jdbcTemplate.update(sql, args);
  }

  @Override
  public void deleteBook(String id) {

    String sql = "delete from t_book where user_id=?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public void updateBook(Book book) {
    String sql = "update t_book set username=?,ustatus=? where user_id=?";
    Object[] args = {book.getUsername(), book.getUstatus(), book.getUserId()};
    jdbcTemplate.update(sql, args);
  }

  @Override
  public Long selectCount() {
    String sql = "select count(*) from t_book";
    Long count = jdbcTemplate.queryForObject(sql, Long.class);
    return count;
  }

  @Override
  public Book findBookInfo(String id) {
    String sql = "select * from t_book where user_id=?";
    Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
    return book;
  }

  @Override
  public List<Book> findAllBook() {
    String sql="select * from t_book";
    List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    return bookList;
  }

  @Override
  public void batchAddBook(List<Object[]> batchArgs) {
    String sql="insert into t_book values(?,?,?)";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
  }

  @Override
  public void batchUpdate(List<Object[]> batchArgs) {
    String sql = "update t_book set username=?,ustatus=? where user_id=?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
  }

  @Override
  public void batchDelete(List<Object[]> batchArgs) {
    String sql="delete from t_book where user_id=?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
  }


}
