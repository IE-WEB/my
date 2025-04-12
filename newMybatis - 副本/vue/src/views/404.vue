

<template>
<div style="text-align: center">
  <img src="@/assets/img_1.png"
       style="width: 70%"
  >
  <div> <el-button type="primary" style="width: 200px;height: 50px; font-size: 20px;padding: 20px " @click="goHome">返回主页 </el-button></div>
</div>
</template>


import router from "@/router/index.js";
import {defineComponent} from "vue";
<script  setup>
import router from "@/router/index.js";

import {defineComponent} from "vue";

const goHome = () =>{
   router.push("/Manager/Home")
};

</script>