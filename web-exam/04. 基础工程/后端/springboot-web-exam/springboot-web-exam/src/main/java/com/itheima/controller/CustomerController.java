package com.itheima.controller;


import com.itheima.anno.Log;
import com.itheima.pojo.BarChartData;
import com.itheima.pojo.Customer;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

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
    @Log
    @GetMapping
    public Result page(String name, Integer gender, String phone, String qq,
                       @RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean =customerService.page(name, gender, phone, qq, page, pageSize);
        return new Result().success(pageBean);
    }

    /**
     * 删除客户
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        customerService.delete(ids);
        return Result.success();
    }

    /**
     * 添加客户
     * @param customer
     * @return
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Customer customer){
        customerService.save(customer);
        return Result.success();
    }

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    @Log
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Customer customer = customerService.getById(id);
        return Result.success(customer);
    }


    /**
     * 修改客户的信息
     * @param customer
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Customer customer){
        customerService.update(customer);
        return Result.success();
    }

    /**
     * 各个年龄段年龄统计
     * @return
     */
    @Log
    @GetMapping("/ageReport")
    public Result ageReport(){
        BarChartData barChartData = customerService.ageReport();
        return Result.success(barChartData);
    }

}
