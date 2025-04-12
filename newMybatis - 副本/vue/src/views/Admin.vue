<template>
  <div >
    <el-Card >
      <el-input v-model="data.name" placeholder="请输入内容" :prefix-icon="Search" style="width: 200px;margin-right: 10px"></el-input>
      <el-button type="primary" @click="load">查询 </el-button>
      <el-button type="warning" @click="reset">重置 </el-button>
    </el-Card>
  </div>

  <div style="margin-top: 10px" >
    <el-Card  >
      <el-button type="primary" @click="handleAdd">新增 </el-button>
      <el-button type="warning" @click="delBatch">删除 </el-button>
      <!-- <el-button type="info">导入 </el-button> -->
      <!-- <el-button type="success">导出 </el-button> -->
    </el-Card>
  </div>
  <div>
    <el-table :data="data.tableData" stripe style="width: 100%" @selection-change="handleselectionchange">
      <el-table-column type="selection" />
      <el-table-column prop="id" label="ID" width="180" />
      <el-table-column prop="username" label="账号" width="180" />
      <el-table-column prop="name" label="姓名" width="180" />
      <el-table-column prop="number" label="年龄" width="180" />
      <el-table-column prop="gender" label="性别" width="180" />
      <el-table-column prop="des" label="个人介绍" show-overflow-tooltip/>
      <el-table-column prop="deptName" label="部门" />
      <el-table-column  label="操作" >
        <template #default="scope">
          <el-button type="primary" :icon="Edit" @click="handleUpdate(scope.row)" >编辑</el-button>
          <el-button type="danger" :icon="Delete"  @click="deleteData(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>

      <el-pagination
          @size-change="load"
          @current-change="load"
          v-model:current-page="data.pageCurrent"
          :page-sizes="[5 , 10 , 15 , 20]"
          v-model:page-size="data.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          style="background: white"

          :total="data.total"
      >
      </el-pagination>

    </div>
    <el-dialog v-model="data.formVisible" title="员工信息" width="500px" distroy-on-close>
      <el-form :model="data.form" style="padding-right: 50px;padding-top: 8px" label-width="80px" ref="formRef" :rules="data.rules" >
        <el-form-item label="账号" prop="username">
          <el-input v-model="data.form.username" autocomplete="off" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"></el-input>
        </el-form-item>
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
      </el-form>
      <div slot="footer" class="dialog-footer" style="padding-top: 20px">
        <el-button @click="data.formVisible= false">取 消</el-button>
        <el-button type="primary" @click="save" >确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script setup>
import {reactive,ref} from "vue";
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import request from "@/util/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  name: null,
  tableData: [],
  pageCurrent: 1,
  pageSize: 5,
  total: 0,
  formVisible: false,
  form: {
    name: '',
    gender: '',
    number: null,
    des: '',
    id:'',
    ids:[],
  },
  rules:{
    username:[
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    name:[
      {required:true,message:'请输入名称',trigger:'blur'}
    ]
  }
});

const load = () => {
  request.get("/employee/selectPage", {
    params: {
      num: data.pageCurrent,
      size: data.pageSize,
      name: data.name
    }
  }).then(res => {
    console.log(res.data);
    data.tableData = res.data.list;
    data.total = res.data.total;
  });
};

const reset = () => {
  data.name = null;
  load();
};

const handleAdd = () => {
  data.formVisible = true;
  // 清空脏数据
  data.form = {
    name: '',
    gender: '',
    number: null,
    des: '',
    id:'',
  };
};

const save = () => {
  //判断是post还是put
  formRef.value.validate((valid =>{
    if(valid){

      data.form.id ? update():add()
    }
  }))

}
const add=()=>{
  request.post("/employee/add", data.form).then(res => {//新增的对象没有id
    if (res.code === '200') {
      data.formVisible = false;
      ElMessage.success('操作成功');
      load(); // 加载最新数据
    } else {
      ElMessage.error(res.data.msg);
    }
  })
}
const update=()=>{
  request.put("/employee/update", data.form).then(res => {
    if (res.code === '200') {
      data.formVisible = false;
      ElMessage.success('操作成功');
      load(); // 加载最新数据
    } else {
      ElMessage.error(res.data.msg);
    }
  })
}

const deleteData=(id)=>{
  ElMessageBox.confirm("请确认是否删除(删除后无法恢复)","删除确认",{type:'warning'}).then(()=>{
    request.delete("/employee/delete/"+id).then(res=>{
      if (res.code === '200') {

        ElMessage.success('操作成功');
        load(); // 加载最新数据
      } else {
        ElMessage.error(res.data.msg);
      }

    })
  })


}

const  handleUpdate=(row)=>{
  data.form=JSON.parse(JSON.stringify(row));
  data.formVisible=true;
}
const handleselectionchange=(rows)=>{

  data.ids=rows.map(row=>row.id)

}

const delBatch=()=>{
  if(data.ids.length===0){
    ElMessage.warning('选择数据')
  }
  ElMessageBox.confirm("请确认是否删除(删除后无法恢复)","删除确认",{type:'warning'}).then(()=>{
    request.delete("/employee/deleteBatch",{data:data.ids}).then(res=>{
      if (res.code === '200') {
        ElMessage.success('操作成功');
        load(); // 加载最新数据
      } else {
        ElMessage.error(res.msg);
      }

  }).catch()
})

}

const formRef=ref()

load();
</script>