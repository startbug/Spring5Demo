package com.ggs.spring5.service;

import com.ggs.spring5.dao.BookDao;
import com.ggs.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/8 23:04
 */
@Service
public class BookService {
  @Autowired private BookDao bookDao;

  // 添加方法
  public void addBook(Book book) {
    bookDao.add(book);
  }
  // 添加方法
  public void updateBook(Book book) {
    bookDao.updateBook(book);
  }
  // 添加方法
  public void deleteBook(String id) {
    bookDao.deleteBook(id);
  }
//查询记录数
  public Long selectCount() {
    return bookDao.selectCount();
  }

  //查询返回对象
  public Book findBookInfo(String id) {
    return bookDao.findBookInfo(id);
  }

  public List<Book> findAll(){
    return bookDao.findAllBook();
  }
  public void batchAddBook(List<Object[]> batchArgs){
    bookDao.batchAddBook(batchArgs);
  }
  public void batchUpdate(List<Object[]> batchArgs){
    bookDao.batchUpdate(batchArgs);
  }

  public void batchDelete(List<Object[]> batchArgs) {
    bookDao.batchDelete(batchArgs);
  }
}
