<template>
  <div >
    <el-Card >
      <el-input v-model="data.title" placeholder="请输入标题查询" :prefix-icon="Search" style="width: 200px;margin-right: 10px"></el-input>
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
      <el-table-column prop="title" label="标题" width="180" />
      <el-table-column prop="time" label="上传时间" width="180" />
      <el-table-column prop="description" label="文章简介" width="200" show-overflow-tooltip/>
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
    <el-dialog v-model="data.formVisible" title="文章信息" width="500px" distroy-on-close>
      <el-form :model="data.form" style="padding-right: 50px;padding-top: 8px" label-width="80px" ref="formRef"  >
        <el-form-item label="账号" prop="title">
          <el-input v-model="data.form.title" autocomplete="off" placeholder="请输入标题"></el-input>
        </el-form-item>

        <el-form-item label="文章简介">
          <el-input type="textarea" v-model="data.form.description" :rows="3" placeholder="请输入文章介绍"></el-input>
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
  title: null,
  tableData: [],
  pageCurrent: 1,
  pageSize: 5,
  total: 0,
  formVisible: false,
  form: {
    title: '',
    description: "",
    id:'',
    ids:[],
  },
});

const load = () => {
  request.get("/article/selectPage", {
    params: {
      num: data.pageCurrent,
      size: data.pageSize,
      title: data.title
    }
  }).then(res => {
    console.log(res.data);
    data.tableData = res.data.list;
    data.total = res.data.total;
  });
};

const reset = () => {
  data.title = null;
  load();
};

const handleAdd = () => {
  data.formVisible = true;
  // 清空脏数据
  data.form = {
    title: '',
    description: "",
    id:'',
  };
};

const save = () => {
  //判断是post还是put
  formRef.value.validate((valid) => {
    if (valid) {
      data.form.id ? update() : add();
    } else {
      ElMessage.error('表单验证失败，请检查输入内容');
    }
  });
};

const add = () => {
  request.post("/article/add", data.form).then(res => {
    if (res && res.code === '200') {
      data.formVisible = false;
      ElMessage.success('操作成功');
      load(); // 加载最新数据
    } else {
      ElMessage.error(res && res.msg ? res.msg : '新增失败，请稍后重试');
    }
  }).catch(error => {
    ElMessage.error('网络错误，请稍后重试');
    console.error(error);
  });
};

const update = () => {
  request.put("/article/update", data.form).then(res => {
    if (res && res.code === '200') {
      data.formVisible = false;
      ElMessage.success('操作成功');
      load(); // 加载最新数据
    } else {
      ElMessage.error(res && res.msg ? res.msg : '更新失败，请稍后重试');
    }
  }).catch(error => {
    ElMessage.error('网络错误，请稍后重试');
    console.error(error);
  });
};

const deleteData = (id) => {
  ElMessageBox.confirm("请确认是否删除(删除后无法恢复)", "删除确认", { type: 'warning' }).then(() => {
    request.delete("/article/delete/" + id).then(res => {
      if (res && res.code === '200') {
        ElMessage.success('操作成功');
        load(); // 加载最新数据
      } else {
        ElMessage.error(res && res.msg ? res.msg : '删除失败，请稍后重试');
      }
    }).catch(error => {
      ElMessage.error('网络错误，请稍后重试');
      console.error(error);
    });
  });
};

const handleUpdate = (row) => {
  data.form = JSON.parse(JSON.stringify(row));
  data.formVisible = true;
};

const handleselectionchange = (rows) => {
  data.ids = rows.map(row => row.id);
};

const delBatch = () => {
  if (data.ids.length === 0) {
    ElMessage.warning('请选择数据');
    return;
  }
  ElMessageBox.confirm("请确认是否删除(删除后无法恢复)", "删除确认", { type: 'warning' }).then(() => {
    request.delete("/article/deleteBatch", { data: data.ids }).then(res => {
      if (res && res.code === '200') {
        ElMessage.success('操作成功');
        load(); // 加载最新数据
      } else {
        ElMessage.error(res && res.msg ? res.msg : '批量删除失败，请稍后重试');
      }
    }).catch(error => {
      ElMessage.error('网络错误，请稍后重试');
      console.error(error);
    });
  });
};

const formRef = ref();

load();
</script>