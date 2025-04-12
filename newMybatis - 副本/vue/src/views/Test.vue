
<template>
  <div>
    <el-button type="primary" @click="router.back()">单击返回</el-button>
  </div>
  <div>
    欢迎大家访问
  </div><br/>
  <div>
    {{a}}
  </div>
  <div style="margin-bottom: 20px">
    <input v-model="a">
  </div>
  <div style="margin-bottom: 20px">
    <span style="color: red" v-if="b.name==='佩奇'"> 小猪佩奇</span>
    <span style="color: blue" v-if="b.name==='乔治'" > 小猪乔治</span>
    <span style="color: hotpink" v-else> pig</span>
  </div><br./>
  <div>
    <select style="width: 200px;text-align: center">
      <option disabled>--请选择--</option>
      <option v-for="item in b.fruits">{{item}}</option>
    </select>
  </div>
  <div>
    <button @click="click">点我好运</button>
  </div>
  <div>
    <div style="b.box ">
    </div>
  </div>
  <div>
    <el-button :style=" {color: 'red'}">aaa</el-button>
    <el-button type="primary" plain>Primary</el-button>
    <el-button type="success" bg="">Success</el-button>
    <el-button type="info" >Info</el-button>
    <el-button type="warning">Warning</el-button>
    <el-button type="danger">Danger</el-button>
  </div>

  <div>
    <span >
      <el-icon :size="30" style="top: 4px">
        <View/>
      </el-icon>100
    </span>
  </div>
  <el-input size="small"
            v-model="b.b"
            style="width: 240px;"
            placeholder="input"
            :prefix-icon="Search"
  />
  <el-input size="large"
            v-model="b.b"
            style="width: 240px;"
            placeholder="input"
            :prefix-icon="Search"
  />

</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {Search} from "@element-plus/icons-vue";
import * as data from "element-plus/es/locale/index";
import router from "@/router/index.js";

onMounted(()=>{
  console.log("页面加载完成")
})
const  a=ref(111)
const b=reactive({
  id: router.currentRoute.value.query.id,
  name: router.currentRoute.value.query.name,
  z: "aaaaa",
  b:  123,
  //.name: "aa",
  fruits: ["香蕉","苹果","橘子"],
  box: {
    width: "100px",
    height: "50px",
    background: "red"
  }
})
console.log("传递的id=",b.id)
console.log("传递的name=",b.name)
const click = () =>{
  alert("好运+1")
}


</script>
