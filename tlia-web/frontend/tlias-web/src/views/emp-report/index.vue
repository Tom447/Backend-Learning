<template>
  <div class="chart-container">

    <div  style="width: 50%; ">
      <h1>员工性别统计</h1> <br>
      <div id="myChart1"  style="width: 100%; height: 500px"></div>
    </div>

    <div  style="width: 50%; ">
      <h1>员工职位统计</h1> <br>
      <div id="myChart2"  style="width: 100%; height: 500px"></div>
    </div>

  </div>
</template>

<script>
import * as echarts from 'echarts'
import { getGenderData, getJobData } from "@/api/report.js";

export default {
  data() {
    return {
      genderCountList: [],
      jobCategoryList:[],
      jobDataList:[]
    }
  },
  mounted() { 
    this.getGenderData(); //获取员工性别统计数据
    this.getJobData(); //获取员工职位统计数据
  },

  methods: {
    //获取员工性别统计数据
    getGenderData(){
      getGenderData().then((result) => {
          if(result.data.code == 1){
             this.genderCountList = result.data.data;
             
             this.initChart1();
          }
      });
    },

    //获取员工职位统计数据
    getJobData(){
      getJobData().then((result) => {
          if(result.data.code == 1){
             this.jobCategoryList = result.data.data.categoryList;
             this.jobDataList = result.data.data.dataList;

             this.initChart2();
          }
      });
    },

    initChart1() {
      // 获取图表容器, 创建图表实例
      let myChart = echarts.init(document.querySelector('#myChart1'));
       
      let option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [{
            name: '员工性别统计',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: this.genderCountList
          }
        ]
      };

      // 绘制图表
      myChart.setOption(option);
    },


    initChart2() {
      // 获取图表容器, 创建图表实例
      let myChart = echarts.init(document.querySelector('#myChart2'));
       
      let option = {
        xAxis: {
          type: 'category',
          data: this.jobCategoryList
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.jobDataList,
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