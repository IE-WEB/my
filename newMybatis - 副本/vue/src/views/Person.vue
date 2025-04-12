<script setup>
import {reactive,ref} from "vue";
import request from "@/util/request.js";
import {ElMessage} from "element-plus";


const handleAvatarSuccess = (res) =>{
    console.log(res.data)
  data.form.avatar =res.data

}

const data=reactive({
  user: JSON.parse(localStorage.getItem("xm-pro-user")),
  form:{},
  rules:{
    username:[
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    name:[
      {required:true,message:'请输入名称',trigger:'blur'}
    ]
  }
})
const formRef=ref()
if(data.form.role==='EMP'){
  request.get('/employee/selectById/'+data.form.id).then(res=>{
    data.form=res.data
  })
}else {
  data.form=data.user
}

const emit=defineEmits(['updateUser'])
const updateUser = () => {
  if (data.user.role === 'EMP') {
    request.put('/employee/update', data.form).then(res => {
      ElMessage.success('更新成功')
      localStorage.setItem('xm-pro-user', JSON.stringify(data.form))
      emit('updateUser')
    })
  } else {
    if (data.user.role === 'ADMIN') {
      request.put('/admin/update', data.form).then(res => {
        ElMessage.success('更新成功')
        localStorage.setItem('xm-pro-user', JSON.stringify(data.form))
        emit('updateUser')
      })
    }

  }


}

</script>

<template>
<div style="width:45% ;padding: 30px 20px">
  <el-Card>
    <el-form :model="data.form" style="padding-right: 50px;padding-top: 8px" label-width="80px" ref="formRef" :rules="data.rules" >
      <div>
        <el-form-item label="头像" >
          <div style="display: flex;width: 100%" justify-center="center" >
            <el-upload
                class="avatar-uploader"
                action="http://localhost:8080/files/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
            >
            <img v-if="data.form.avatar" :src="data.form.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          </div>
        </el-form-item>

      </div>
      <el-form-item  label="账号" prop="username">
        <el-input disabled v-model="data.form.username" autocomplete="off" placeholder="请输入账号"></el-input>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"></el-input>
      </el-form-item>
      <div v-if="data.user.role==='EMP'">
        <el-form-item label="性别" label-width="80px">
          <el-radio-group v-model="data.form.gender">
            <el-radio value="男" label="男"></el-radio>
            <el-radio value="女" label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" >
          <el-input-number style="width: 180px" :min="18" v-model="data.form.number" autocomplete="off" placeholder="请输入年龄"></el-input-number>
        </el-form-item>
        <el-form-item label="个人介绍">
          <el-input type="textarea" v-model="data.form.des" :rows="3" placeholder="请输入个人介绍"></el-input>
        </el-form-item>
      </div>

      <div style="text-align: center">
        <el-button type="primary" @click="updateUser">确认修改</el-button>
      </div>

    </el-form>
  </el-Card>

</div>
</template>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;

}
.avatar-uploader .el-upload:hover {
  border-color: #1b1b1c;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 150px;
  height: 150px;
  display: flex;
}
</style>
