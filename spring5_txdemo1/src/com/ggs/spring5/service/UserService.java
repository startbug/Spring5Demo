package com.ggs.spring5.service;

import com.ggs.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Starbug
 * @date: 2020/6/10 22:19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class UserService {
    @Autowired
    private UserDao userDao;

    //转账的方法
    public void transferMoney(){
        //lucy减钱
        userDao.decreaseMoney();

        //制造异常
//        int i=10/0;

        //marry加钱
        userDao.increaseMoney();
    }

    //转账的方法2(换个名字)
    public void zhuanzhang(){
        //lucy减钱
        userDao.decreaseMoney();

        //制造异常
        int i=10/0;

        //marry加钱
        userDao.increaseMoney();
    }

}
