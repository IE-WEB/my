<script setup>
import {reactive,ref} from "vue";
import {User,Lock}from "@element-plus/icons-vue"
import request from "@/util/request.js";
import {ElMessage} from "element-plus";

const validatePass2 = (rules, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'));
  } else if (value !== data.form.password) {
    callback(new Error('两次输入密码不一致!'));
  } else {
    callback();
  }
};
const data=reactive({
  form:{},
  rules:{
    username:[
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    password:[
      {required:true,message:'请输入密码',trigger:'blur'}
    ],
    confirm:[
      {validator: validatePass2,trigger:"blur"}
    ]
  }

})

const formRef=ref()
const register = () =>{
  formRef.value.validate((valid) => {
    if(valid) {
      request.post('/register',data.form).then( res =>{
         if(res.code === '200'){
           ElMessage.success('操作成功');
           location.href='/login'
           //存储用户信息

         }else{
           ElMessage.error("注册失败")
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
     <div style="font-family: Arial, sans-serif;padding-bottom: 20px;color:#1b1b1c;text-align: center">欢 迎 注 册</div>
    <el-form-item prop="username">
      <el-input v-model="data.form.username" placeholder="账号" size="large" prefix-icon="User" ></el-input>
    </el-form-item >
     <el-form-item prop="password">
       <el-input v-model="data.form.password" placeholder="密码" size="large" prefix-icon="Lock" show-password></el-input>
     </el-form-item>
     <el-form-item prop="confirm">
       <el-input v-model="data.form.confirm" placeholder="确认密码" size="large" prefix-icon="Lock" show-password></el-input>
     </el-form-item>
     <div style="margin-bottom: 20px">
       <el-button size="large" style="width: 100%" type="primary" @click="register">注册</el-button>
     </div>
     <div style="text-align: right">已经有账号？<a style="color:#6e05f1;text-decoration: none" href="/login" >登录</a> </div>
  </el-form>
     </div>
  </div>
</template>

<style scoped>
.login-container{
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/panda.jpg");
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

