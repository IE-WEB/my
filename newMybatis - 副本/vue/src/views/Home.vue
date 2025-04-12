<template>
  <div>
    <RouterLink to="/test">
      跳转test页面
    </RouterLink>
  </div>
  <div>
    <el-button type="primary" @click="router.push('/Manager/Test?id=2&name=小李')">点击实现跳转
    </el-button>

  </div>

  <div>
    <el-input v-model="data.input" placeholder="请输入内容" style="width: 200px;top: 20px"
              :prefix-icon="Search"></el-input>{{data.input}}<br/>
    <el-input
        type="textarea"
        :rows="10"
        placeholder="请输入内容"
        style="width: 200px"
        v-model="data.textarea">
    </el-input>
  </div>
  <div>
    <el-select v-model="data.value" placeholder="请选择" style="width: 200px;height: 20px" size="small" clearable multiple>
      <el-option
          v-for="item in data.options"
          :key="item"
          :label="item"
          :value="item">
      </el-option>
    </el-select>
  </div>
  <div>
    <el-radio-group v-model="data.radio">
      <el-radio :label="'男'" value="男">男</el-radio>
      <el-radio :label="'女'" value="女">女</el-radio>
    </el-radio-group> <span style="margin-left: 50px">{{data.radio}}</span>
  </div>
  <div>
    <el-radio-group v-model="data.tags">
      <el-radio-button label="上海" value="1"></el-radio-button>
      <el-radio-button label="北京" value="2"></el-radio-button>
      <el-radio-button label="广州" value="3"></el-radio-button>
      <el-radio-button label="深圳" value="4"></el-radio-button>
    </el-radio-group>
  </div>
  <div>
    <el-checkbox-group v-model="data.checkList">
      <el-checkbox label="复选框 A" ></el-checkbox>
      <el-checkbox label="复选框 B"></el-checkbox>
      <el-checkbox label="复选框 C"></el-checkbox>
      <el-checkbox label="禁用" disabled></el-checkbox>
      <el-checkbox label="选中且禁用" disabled></el-checkbox>
    </el-checkbox-group>
  </div>
  <el-image
      style="width: 100px; height: 100px"
      :src="img"
      :preview-src-list="[img,'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
          'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg']">
  </el-image>
  <el-carousel indicator-position="outside">
    <el-carousel-item v-for="item in data.img" :key="item">
      <div class="carousel-content">
        <img
            :src="item"
            class="carousel-image"
        >
        <div class="carousel-caption">
          <h2>{{ data.title }}</h2>
          <el-button type="primary">了解更多</el-button>
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>
  <div>
    <el-date-picker
        v-model="data.value1"
        type="date"
        placeholder="选择日期"
        size="large"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
    >
    </el-date-picker>{{data.value1}}
  </div>
  <div>
    <el-date-picker
        v-model="data.value0"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
    >
    </el-date-picker> {{data.value0[0]}}    {{ data.value0[1]}}
  </div>

  <div>
    <el-table
        :data="data.tableData"
        style="width: 100%"
        stripe>
      <el-table-column
          prop="date"
          label="日期"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
    </el-table>

  </div>
  <template>
    <el-pagination size="small" layout="prev, pager, next" :total="50" />
    <el-pagination
        size="small"
        background
        layout="prev, pager, next"
        :total="50"

    />
  </template>


</template>

<script setup>
import { reactive } from "vue";
import {Search} from "@element-plus/icons-vue";
import  img from "@/assets/logo.svg"
import  p1 from "@/assets/p1.jpg"
import  p2 from "@/assets/p2.jpg"
import  p3 from "@/assets/p3.jpg"
import  p4 from "@/assets/p4.jpg"
import router  from "@/router/index.js";

import request from "@/util/request.js"

const data = reactive({
  title: "欢迎访问",
  input: '',
  textarea: "hello",
  options: ["苹果","香蕉","橘子","葡萄"],
  value: '',
  radio: '',
  tags: '3',
  checkList: [],
  img: [p1,p2,p3,p4],
  value1:'',
  value0:'',
  pageCurrent: 1,
  pageSize:5,
  tableData:[{
    id: 1,date: "2025-3-30",name:"张三",address:"安徽合肥"},
    {
      id: 2,date: "2025-3-30",name:"张三",address:"安徽合肥"
    },{
      id: 3,date: "2025-3-30",name:"张三",address:"安徽合肥"
    }],
  empoyeeList: []
});

request.get("/employee/selectAll").then(res => {

  data.empoyeeList = res.data;
}).catch(error => {
  console.error('请求出错:', error);
});
</script>