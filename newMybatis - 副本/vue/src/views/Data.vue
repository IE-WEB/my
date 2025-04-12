<template>
  <div>
    <div style="display: flex;grid-gap: 10px">
      <div class="el-card" style="flex: 1;height:400px" id="bar"></div>
      <div class="el-card" style="flex: 1;height:400px" id="line"></div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";
import * as echarts from 'echarts';
import request from "@/util/request.js";

const barOption = {
  legend: {
    data: ['人数']
  },
  title: {
    text: '各部门员工的数量'
  },
  tooltip: {},
  // 部门名称
  xAxis: {
    data: []
  },
  // 人数
  yAxis: {},
  series: [
    {
      name: '人数',
      type: 'bar',
      data: [],
      itemStyle: {
        normal: {
          color: '#FF6347' // 设置柱状图柱子的颜色，这里设置为珊瑚红，你可以根据需求修改颜色值
        }
      }
    }
  ]
};

const lineOption = {
  legend: {
    data: ['数据']
  },
  title: {
    text: '最近七天发布文章的数量'
  },
  tooltip: {},
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '数据',
      type: 'line',
      data: [],
      itemStyle:{
        normal:{
          color: function (){
            return "#"+Math.floor(Math.random()* (256*256*256-1)).toString(16)
          }
        }
      }

    }
  ]
};

const data = reactive({});

onMounted(() => {
  // 初始化柱状图
  const barChart = echarts.init(document.getElementById('bar'));
  const lineChart = echarts.init(document.getElementById('line'));


  // 请求柱状图数据
  request.get('/barData').then(res => {
    barOption.xAxis.data = res.data.department;
    barOption.series[0].data = res.data.count;
    barChart.setOption(barOption);
  }).catch(error => {
    console.error('获取柱状图数据失败:', error);
  });

  // 初始化柱状图


  request.get('/lineData').then(res => {
    lineOption.xAxis.data = res.data.date;
    lineOption.series[0].data = res.data.count;
    lineChart.setOption(lineOption);
  }).catch(error => {
    console.error('获取折线图数据失败:', error);
  });
})



// 请求折线图数据

</script>

<style scoped>

</style>