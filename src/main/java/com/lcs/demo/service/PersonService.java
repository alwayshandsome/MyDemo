package com.lcs.demo.service;

import com.lcs.demo.dao.LoginDao;
import com.lcs.demo.dao.PersonDao;
import com.lcs.demo.pojo.Login;
import com.lcs.demo.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private LoginDao loginDao;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username,String password){
        List<Login> loginList = loginDao.login(username,password);
        System.out.println("loginList = \n"+loginList+"loginList.size = "+loginList.size());
        return loginList.size() > 0;
    }

    /**
     * 通过名字查询所有数据信息
     * @param name
     * @return
     */
    public List<Person> findByName(String name){
        return personDao.findByName(name);
    }

    /**
     * 通过地址查询所有数据信息
     * @param address
     * @return
     */
    public List<Person> findByAddress(String address){
        return personDao.findByAddress(address);
    }

    /**
     * 通过姓名和地址查询所有数据信息
     * @param name
     * @param address
     * @return
     */
    public List<Person> findByNa_Ad(String name,String address){
        return personDao.findByNa_Ad(name,address);
    }

    /**
     * 增
     * @param name
     * @param age
     * @param address
     * @return
     */
    public int add(String name,int age,String address){
        return personDao.add(name,age,address);
    }

    /**
     * 删
     * @param name
     * @return
     */
    public int delete(String name){
        return personDao.delete(name);
    }

    /**
     * 改
     * @param id
     * @param name
     * @return
     */
    public int modify(int id,String name){
        return personDao.modify(id,name);
    }
}







