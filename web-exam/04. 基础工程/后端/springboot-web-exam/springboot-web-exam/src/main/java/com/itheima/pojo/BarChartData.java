package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 柱状图结果封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarChartData {
    private List categoryList;
    private List datas;
}
