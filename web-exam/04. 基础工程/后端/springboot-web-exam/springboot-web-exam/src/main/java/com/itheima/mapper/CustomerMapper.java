package com.itheima.mapper;


import com.itheima.pojo.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /**
     *
     * @param name
     * @param gender
     * @param phone
     * @param qq
     * @return
     */
    List<Customer> list(String name, Integer gender, String phone, String qq);

    void delete(List<Integer> ids);

    @Insert("insert into crm_customer(name, phone, gender, age, qq,description, create_time, update_time)values (#{name},#{phone},#{gender},#{age},#{qq},#{description},#{createTime},#{updateTime})")
    void insert(Customer customer);

    @Select("select * from crm_customer where id=#{id}")
    Customer getById(Integer id);

    void update(Customer customer);

    @Select("select * from crm_customer")
    List<Customer> getListNoById();
}
