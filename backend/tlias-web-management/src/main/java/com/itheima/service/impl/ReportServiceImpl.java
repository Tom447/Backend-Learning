package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.BarChartData;
import com.itheima.pojo.PieChartData;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<PieChartData> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public BarChartData getEmpJobData() {
        //1. 调用mapper获取原始数据
        List<Map<String, Object>> mapList = empMapper.countEmpJobData();
        System.out.println(mapList);

        //2. 就是进行逻辑处理
        if (!CollectionUtils.isEmpty(mapList)){
            List<Object> categoryList = mapList.stream().map(map -> {
                return map.get("job");
            }).collect(Collectors.toList());

            List<Object> dataList = mapList.stream().map(map -> {
                return map.get("jobcount");
            }).collect(Collectors.toList());

            return new BarChartData(categoryList , dataList);
        }

        return new BarChartData();
    }
}
