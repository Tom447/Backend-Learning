package com.itheima.service;

import com.itheima.pojo.PageBean;
import com.itheima.pojo.Student;

import java.util.List;

public interface StudentService {

    /**
     * 添加学生信息
     * @param student
     */
    void save(Student student);

    /**
     * 条件分页查询
     * @param name
     * @param no
     * @param clazzId
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(String name, String no, Integer degree,Integer clazzId, Integer page, Integer pageSize);

    /**
     * 根据ID查询学生信息
     * @param id
     * @return
     */
    Student getInfo(Integer id);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);

    /**
     * 删除学生信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 违纪处理
     * @param id
     * @param score
     */
    void violationHandle(Integer id, Integer score);
}
