<template>
  <div style="width:60% ;padding: 30px 20px">
    <el-Card>
      <el-form :model="data.form" style="padding-right: 50px;padding-top: 8px" label-width="80px" ref="formRef" :rules="data.rules" >
        <el-form-item  label="原密码" prop="password">
          <el-input show-password v-model="data.form.password" autocomplete="off" placeholder="请输入原密码"></el-input>
        </el-form-item>
        <el-form-item  label="新密码" prop="newPassword">
          <el-input show-password v-model="data.form.newPassword" autocomplete="off" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item  label="确认密码" prop="confirmPassword" >
          <el-input show-password v-model="data.form.confirmPassword" autocomplete="off" placeholder="确认密码"></el-input>
        </el-form-item>

        <div style="text-align: center">
          <el-button type="primary" @click="updatePassword">确认修改</el-button>
        </div>
      </el-form>
    </el-Card>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import request from "@/util/request.js";
import { ElMessage } from "element-plus";

const validatePass2 = (rules, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'));
  } else if (value !== data.form.newPassword) {
    callback(new Error('两次输入密码不一致!'));
  } else {
    callback();
  }
};

const data = reactive({
  user: null,
  form: {},
  rules: {
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' }
    ],
    newPassword: [
      { required: true, message: '请输入新密码', trigger: 'blur' }
    ],
    confirmPassword: [
      { validator: validatePass2, trigger: "blur" }
    ]
  }
});

const formRef = ref();

// 检查 localStorage 中的用户数据
const userData = localStorage.getItem("xm-pro-user");
if (userData) {
  data.user = JSON.parse(userData);
}

const updatePassword = () => {
  if (data.user) {
    data.form.id = data.user.id;
  }
  formRef.value.validate((valid) => {
    if (valid) {
      request.put('/updatePassword', data.form)
          .then((res) => {
            if (res.code === '200') {
              ElMessage.success('修改成功');
              localStorage.removeItem('xm-pro-user');
            } else {
              ElMessage.error(res.msg);
            }
          })
          .catch((error) => {
            ElMessage.error('网络错误，请稍后重试');
            console.error(error);
          });
    }
  });
};
</script>

<style scoped>

</style>