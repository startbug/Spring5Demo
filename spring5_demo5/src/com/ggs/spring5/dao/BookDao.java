package com.ggs.spring5.dao;

import com.ggs.spring5.entity.Book;

import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/8 23:39
 */
public interface BookDao {
    void add(Book book);

    void deleteBook(String id);

    void updateBook(Book book);

    Long selectCount();

    Book findBookInfo(String id);

    List<Book> findAllBook();

    void batchAddBook(List<Object[]> batchArgs);

    void batchUpdate(List<Object[]> batchArgs);

    void batchDelete(List<Object[]> batchArgs);
}
