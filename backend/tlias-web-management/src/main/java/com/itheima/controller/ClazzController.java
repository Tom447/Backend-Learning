package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 新增班级
     * @param clazz
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 条件分页查询班级
     * @param name
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result page(String name , @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page ,  @RequestParam(defaultValue = "10")Integer pageSize){
        PageBean pageBean = clazzService.page(name , begin , end , page , pageSize);
        return Result.success(pageBean);
    }

    /**
     * 根据ID查询班级详情
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public Result getInfo(@PathVariable Integer id){
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * 更新班级信息
     * @param clazz
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 根据ID删除班级
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        clazzService.deleteById(id);
        //TODO 后续完善
        return Result.success();
    }

    /**
     * 查询全部班级
     * @return
     */
    @GetMapping("/list")
    public Result findAll(){
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}
