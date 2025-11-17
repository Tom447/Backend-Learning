package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //原始分页查询
    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 调用mapper接口, 获取总记录数 . total
        Long total = empMapper.count();

        //2. 调用mapper接口, 获取结果列表 . rows
        Integer start = (page - 1) * pageSize ;
        List<Emp> empList = empMapper.page(start, pageSize);

        //3. 封装结果, 并返回
        return new PageBean(total , empList);
    }*/


    //2. 基于PageHelper分页插件实现分页查询
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1. 设置分页参数
        PageHelper.startPage(page , pageSize); // 1,5

        //2. 执行查询
        List<Emp> empList = empMapper.list(name,gender,begin,end); //1. select count(*) from emp ; -----> total
                                              //2. select * from emp limit 0,5 ; --> List<Emp>
        //3. 获取分页结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageBean(p.getTotal(), p.getResult());
    }


    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }


    @Override
    public void save(Emp emp) {
        //1. 补全属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        //2. 调用mapper, 保存数据
        empMapper.insert(emp);
    }


    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }


    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }


    @Override
    public Emp login(Emp emp) {
        return empMapper.getEmpByUsernameAndPassword(emp);
    }

    @Override
    public List<Emp> getlist() {
        return empMapper.getAllEmpNoParam();
    }
}
