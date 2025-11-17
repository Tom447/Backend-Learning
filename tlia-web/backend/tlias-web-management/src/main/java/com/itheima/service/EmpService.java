package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除操作
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 登录方法
     * @param emp
     * @return
     */
    Emp login(Emp emp);

    List<Emp> getlist();
}
