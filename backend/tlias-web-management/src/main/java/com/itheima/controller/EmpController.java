package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("员工分页查询 , page: {}, pageSize: {}, name: {}, gender: {}, begin:{}, end:{}", page, pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page , pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }


    /**
     * 批量删除
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工, ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }


    /**
     * 添加员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("添加员工 , {}", emp);
        empService.save(emp);
        return Result.success();
    }


    /**
     * 根据ID查询班级详情
     * 只接受数字 ID
     * 不然list可能会被误认为是id
     */
    @GetMapping("/{id:\\d+}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID 查询员工信息 , id: {}" , id);
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }

    @GetMapping("/list")
    public Result getlist(){
        log.info("查询员工列表list");
        List<Emp> empList = empService.getlist();
        return Result.success(empList);
    }


    /**
     * 修改员工
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工 , {}" , emp);
        empService.update(emp);
        return Result.success();
    }

}
