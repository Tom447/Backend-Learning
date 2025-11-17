package com.itheima.service;

import com.itheima.pojo.BarChartData;
import com.itheima.pojo.PieChartData;

import java.util.List;

public interface ReportService {
    /**
     * 统计员工性别信息
     * @return
     */
    List<PieChartData> getEmpGenderData();

    /**
     * 统计员工职位信息
     * @return
     */
    BarChartData getEmpJobData();
}
