<script setup>
import {reactive,ref} from "vue";
import {User,Lock} from "@element-plus/icons-vue"
import request from "@/util/request.js";
import {ElMessage} from "element-plus";



const data=reactive({
  form:{ role:"EMP"},
  rules:{
    username:[
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    password:[
      {required:true,message:'请输入密码',trigger:'blur'}
    ],

  }

})
const formRef=ref()
const login = () =>{
  formRef.value.validate((valid) => {
    if(valid) {
      request.post('/login',data.form).then( res =>{
         if(res.code === '200'){
           ElMessage.success('操作成功');
           location.href='/Manager/show'
           //存储用户信息
           localStorage.setItem('xm-pro-user',JSON.stringify(res.data))
         }else{
           ElMessage.error("登录失败")
         }
      })
    }
  })
}
</script>

<template >
  <div class="login-container">
      <div class="login-box">
   <el-form ref="formRef" :model="data.form" style="width: 350px;" :rules="data.rules">
     <div style="font-family: Arial, sans-serif;padding-bottom: 20px;color:#1b1b1c;text-align: center">欢迎来到数据分析系统</div>
    <el-form-item prop="username">
      <el-input v-model="data.form.username" placeholder="账号" size="large" prefix-icon="User" ></el-input>
    </el-form-item >
     <el-form-item prop="password">
       <el-input v-model="data.form.password" placeholder="密码" size="large" prefix-icon="Lock" show-password></el-input>
     </el-form-item>
     <el-form-item prop="role">
       <el-select style="width: 100%;" size="large" v-model="data.form.role">
         <el-option value="ADMIN" label="管理员"></el-option>
         <el-option value="EMP" label="普通用户"></el-option>
       </el-select>
     </el-form-item>
     <div style="margin-bottom: 20px">
       <el-button size="large" style="width: 100%" type="primary" @click="login">登 录</el-button>
     </div>
     <div style="text-align: right">还没有账号？<a style="color:#6e05f1;text-decoration: none" href="/register">注册</a> </div>
  </el-form>
     </div>
  </div>
</template>

<style scoped>
.login-container{
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/data1.jpg");
  background-size: cover;

  display: flex;
  justify-content: center;
  align-items: center;

  .login-box{
  background-position: center;
  background-color: rgba(255, 255, 255, 0.7); /* 透明度为0.7的白色背景 */
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 350px;
}
}

</style>

