package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门
     * @return
     */
    @GetMapping//@RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }


    /**
     * 根据ID删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除部门, ID: {} ", id);
        deptService.delete(id);
        return Result.success();
    }


    /**
     * 新增部门
     */
    @PostMapping
    public Result save(@RequestBody Dept dept){
        log.info("新增部门, 部门信息: {}" , dept);
        deptService.save(dept);
        return Result.success();
    }

    //根据ID查询部门
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询部门, id: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门数据 , dept: {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
