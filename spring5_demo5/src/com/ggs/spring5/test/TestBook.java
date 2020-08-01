package com.ggs.spring5.test;

import com.ggs.spring5.entity.Book;
import com.ggs.spring5.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Starbug
 * @date: 2020/6/10 21:00
 */
public class TestBook {

  @Test
  public void testJdbcTemplate() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    Book book = new Book("1", "zzzz", "zzzz");
    //    bookService.addBook(book);
    //    bookService.updateBook(book);
    bookService.deleteBook("1");
  }

  @Test
  public void testSelect1() {

    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    Long count = bookService.selectCount();
    System.out.println(count);
  }

  @Test
  public void testSelect2() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    Book book = bookService.findBookInfo("1");
    System.out.println(book);
  }

  @Test
  public void testSelect3() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    List<Book> bookList = bookService.findAll();
    bookList.forEach(book -> System.out.println(book));
  }

  @Test
  public void testBatchAddBook() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    List<Object[]> batchArgs=new ArrayList<>();
    Object[] o1={"5","java11","a"};
    Object[] o2={"6","c++11","b"};
    Object[] o3={"7","Mysql11","c"};
    batchArgs.add(o1);
    batchArgs.add(o2);
    batchArgs.add(o3);
    bookService.batchAddBook(batchArgs);
  }

  @Test
  public void testBatchUpdateBook() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    List<Object[]> batchArgs=new ArrayList<>();
    Object[] o1={"java11","a","5"};
    Object[] o2={"c++11","b","6"};
    Object[] o3={"Mysql11","c","7"};
    batchArgs.add(o1);
    batchArgs.add(o2);
    batchArgs.add(o3);
    bookService.batchUpdate(batchArgs);
  }

  @Test
  public void testBatchDeleteBook() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    List<Object[]> batchArgs=new ArrayList<>();
    Object[] o1={"5"};
    Object[] o2={"6"};
    batchArgs.add(o1);
    batchArgs.add(o2);
    bookService.batchDelete(batchArgs);
  }
}
