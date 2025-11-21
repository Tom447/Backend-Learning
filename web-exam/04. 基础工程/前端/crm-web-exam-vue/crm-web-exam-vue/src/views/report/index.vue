<template>
  <div class="chart-container">

    <div  style="width: 80%; ">
      <h1>客户年龄段统计</h1> <br>
      <div id="myChart1"  style="width: 80%; height: 500px"></div>
    </div>

  </div>
</template>

<script>
import * as echarts from 'echarts'
import { getAgeData } from "@/api/report.js";

export default {
  data() {
    return {
      ageGroupList: [], //年龄段列表
      ageCountList: []  //各个年龄段对应的人数
    }
  },

  mounted() { 
    this.getAgeData(); //获取客户的年龄统计数据
  },

  methods: {
    //获取员工性别统计数据
    getAgeData(){
      getAgeData().then((result) => {
          if(result.data.code == 1){
            this.ageGroupList = result.data.data.categories;
            this.ageCountList = result.data.data.datas;
             
            this.initChart1();
          }
      });
    },

    initChart1() {
      // 获取图表容器, 创建图表实例
      let myChart = echarts.init(document.querySelector('#myChart1'));
      
      let option = option = {
        xAxis: {
          type: 'category',
          data: this.ageGroupList
        },
        yAxis: {
          type: 'value'
        },
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [{
              offset: 0, color: '#44c2fd' // 0% 处的颜色
          }, {
              offset: 1, color: '#8c61ff' // 100% 处的颜色
          }],
        },
        series: [
          {
            data: this.ageCountList,
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
  #myChart1{
    margin: auto;
  }
</style>