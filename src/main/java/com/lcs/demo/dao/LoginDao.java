package com.lcs.demo.dao;

import com.lcs.demo.pojo.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao extends JpaRepository<Login,Long> {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select l from Login l where l.username=:username and l.password=:password")
    public List<Login> login(@Param("username") String username, @Param("password") String password);

}
