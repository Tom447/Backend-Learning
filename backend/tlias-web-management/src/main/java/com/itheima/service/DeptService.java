package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部部门
     * @return
     */
    List<Dept> list();

    /**
     * 根据ID删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void save(Dept dept);

    /**
     * 根据ID查询部门
     * @param id
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
