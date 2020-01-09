package com.lcs.demo.dao;

import com.lcs.demo.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//添加事物管理 repository注解
@Repository
public interface PersonDao extends JpaRepository<Person,Long> {
    //nativeQuery = true表示使用原生的SQL语句。

    /**
     * 通过名字查询所有数据信息
     * @param name
     * @return
     */
    @Query(value = "select p from Person p where p.name=:name")
    public List<Person> findByName(@Param("name")String name);

    /**
     * 通过地址查询所有数据信息
     * @param address
     * @return
     */
    @Query(value = "select p from Person p where p.address=:address")
    public List<Person> findByAddress(@Param("address")String address);

    /**
     * 通过名字和地址查询所有数据信息
     * @param name
     * @param address
     * @return
     */
    @Query(value = "select p from Person p where p.name=:name and p.address=:address")
    public List<Person> findByNa_Ad(@Param("name")String name,@Param("address")String address);

    /**
     * 增
     * @param name
     * @param age
     * @param address
     * @return
     */
    @Modifying
    @Query(value = "insert into Person(name,age,address) value(?1,?2,?3)",nativeQuery = true)
    public int add(String name,int age,String address);

    /**
     * 删
     * @param name
     * @return
     */
    @Modifying
    @Query(value = "delete from Person p where p.name=:name")
    public int delete(@Param("name")String name);

    /**
     * 改
     * @param id
     * @param name
     * @return
     */
    @Modifying
    @Query(value = "update Person p set p.name=?2 where p.id=?1",nativeQuery = true)
    public int modify(int id,String name);
}
