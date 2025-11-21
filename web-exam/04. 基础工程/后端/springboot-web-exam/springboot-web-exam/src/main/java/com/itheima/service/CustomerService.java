package com.itheima.service;

import com.itheima.pojo.BarChartData;
import com.itheima.pojo.Customer;
import com.itheima.pojo.PageBean;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerService {


    /**
     * 分页查询
     * @param name
     * @param gender
     * @param phone
     * @param qq
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(String name, Integer gender, String phone, String qq,  Integer page ,
                 Integer pageSize);

    /**
     * 删除ids
     * @param ids
     */
    void delete(List<Integer> ids);

    void save(Customer customer);

    Customer getById(Integer id);

    void update(Customer customer);

    BarChartData ageReport();
}
