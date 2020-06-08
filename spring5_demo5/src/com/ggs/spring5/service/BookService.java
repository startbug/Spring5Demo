package com.ggs.spring5.service;

import com.ggs.spring5.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Starbug
 * @date: 2020/6/8 23:04
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
}
