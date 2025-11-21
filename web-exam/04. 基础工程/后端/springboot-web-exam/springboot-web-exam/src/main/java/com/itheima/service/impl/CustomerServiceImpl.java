package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.CustomerMapper;
import com.itheima.pojo.BarChartData;
import com.itheima.pojo.Customer;
import com.itheima.pojo.PageBean;
import com.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;


    /**
     * 分页查询
     * @param name
     * @param gender
     * @param phone
     * @param qq
     * @param page
     * @param pageSize
     * @return
     *
     */
    @Override
    public PageBean page(String name, Integer gender, String phone, String qq, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Customer> customersList = customerMapper.list(name, gender, phone, qq);
        Page<Customer> p = (Page<Customer>) customersList;
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        customerMapper.delete(ids);
    }

    @Override
    public void save(Customer customer) {
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customerMapper.insert(customer);
    }

    @Override
    public Customer getById(Integer id) {
        return customerMapper.getById(id);
    }

    @Override
    public void update(Customer customer) {
        customer.setUpdateTime(LocalDateTime.now());
        customerMapper.update(customer);

    }

    @Override
    public BarChartData ageReport() {
        BarChartData barChartData = new BarChartData();
        barChartData.setCategoryList(Arrays.asList("0-20", "21-40", "41-60", "61-80", "81以上"));
        Short[] counts = new Short[barChartData.getCategoryList().size()];
        List<Customer> list = customerMapper.getListNoById();
        System.out.println(list.toArray().toString());
        Arrays.fill(counts,(short)0);
        list.stream().forEach(c ->{
            Short age = c.getAge();
            if (age >= 0 && age <= 20) {
                counts[0]++;
            } else if (age >= 21 && age <= 40) {
                counts[1]++;
            } else if (age >= 41 && age <= 60) {
                counts[2]++;
            } else if (age >= 61 && age <= 80) {
                counts[3]++;
            } else if (age >= 81) {
                counts[4]++;
            }
        });
        List<Short> data = Arrays.stream(counts).collect(Collectors.toList());
        barChartData.setDatas(data);
        return barChartData;
    }

}
