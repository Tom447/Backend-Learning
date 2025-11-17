package com.itheima.controller;

import com.itheima.pojo.BarChartData;
import com.itheima.pojo.PieChartData;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empGenderData")
    public Result empGenderData(){
       log.info("统计员工的性别数据");
        List<PieChartData> dataList = reportService.getEmpGenderData();
       return Result.success(dataList);
    }


    @GetMapping("/empJobData")
    public Result empJobData(){
        log.info("统计员工的职位数据");
        BarChartData barChartData = reportService.getEmpJobData();
        return Result.success(barChartData);
    }
}
