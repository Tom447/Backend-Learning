<template>
  <div class="chart-container">

    <div  style="width: 100%; ">
      <h1>班级人数统计</h1> <br>
      <div id="myChart1"  style="width: 100%; height: 500px"></div>
    </div>

  </div>
</template>

<script>
import * as echarts from 'echarts'
import { getStudentData } from "@/api/report.js";

export default {
  data() {
    return {
      clazzCountList: [],
      studentCountList: []
    }
  },

  mounted() { 
    this.getStudentData(); //获取班级学生统计数据
  },

  methods: {
    //获取班级学生统计数据
    getStudentData(){
      getStudentData().then((result) => {
          if(result.data.code == 1){
             this.clazzCountList = result.data.data.categoryList;
             this.studentCountList = result.data.data.dataList;
             
             this.initChart1();
          }
      });
    },
    
    initChart1() {
      // 获取图表容器, 创建图表实例
      let myChart = echarts.init(document.querySelector('#myChart1'));
       
      let option = {
        xAxis: {
          type: 'category',
          data: this.clazzCountList
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.studentCountList,
            type: 'bar'
          }
        ]
      };
      
      // 绘制图表
      myChart.setOption(option);
    }
  }
};
</script>

<style>
  h1 {
    text-align: center;
  }

  .chart-container {
    display: flex;
  }
  .chart-container > div {
    flex: 1;
  }
</style>