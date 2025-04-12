<template>
  <div>
    <!--  开始-->
    <div style="height: 60px;background-color: #F6F7F8FF;display: flex;align-items: center;width: 100%">
      <div style="width: 200px;display: flex ;align-items: center;font-size: 25px;text-align: center;padding-top: 3px;color: #7e7edd">
        <img src="@/assets/dataicon.png" style="width: 25%;height: auto">
        <span style="font-size: 25px;text-align: center;padding-top: 3px;color: black">数据分析系统</span>
      </div>
      <div style="flex: 1"></div>
      <div style="width: fit-content;display: flex;align-items: center;margin-right: 10px">
        <!-- 检查 data.user 是否存在 -->
        <img :src="data.user && data.user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" style="width: 40px;height: 40px;margin-right: 10px;border-radius: 50%">
        <span v-if="data.user">{{data.user.name}}</span>
      </div>
    </div>

    <!--  下面开始-->
    <div style="display: flex">
      <div style="width: 200px;border-right: 1px solid #1b1b1c;min-height: calc(100vh - 60px)">
        <el-menu router>
          <el-menu-item index="/Manager/Home">
            <el-icon><House /></el-icon>导航菜单
          </el-menu-item>
          <el-sub-menu index="1">

            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/Manager/admin">管理员信息</el-menu-item>
            <el-menu-item index="/Manager/employee">员工信息</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="/Manager/person">
            <el-icon><UserFilled /></el-icon>个人信息
          </el-menu-item>
          <el-menu-item index="/Manager/article">
         <el-icon><reading></reading></el-icon>文章
          </el-menu-item>
          <el-menu-item index="/Manager/data">
            <el-icon><DataAnalysis /></el-icon>数据统计
          </el-menu-item>
          <el-menu-item index="/Manager/show">
            <el-icon><DataAnalysis /></el-icon>数据显示
          </el-menu-item>
          <el-menu-item index="/Manager/department">
            <el-icon><office-building/></el-icon>部门
          </el-menu-item>
          <el-menu-item index="/Manager/data">
            <el-icon><EditPen /></el-icon>留言
          </el-menu-item>
          <el-menu-item index="/Manager/data">
            <el-icon><BellFilled /></el-icon>通知
          </el-menu-item>
          <el-menu-item index="/Manager/password">
            <el-icon><Lock/></el-icon>修改密码
          </el-menu-item>
          <el-menu-item @click="loginout">
            <el-icon><SwitchButton /></el-icon>退出
          </el-menu-item>
        </el-menu>
      </div>

      <!--    右侧主题的区域-->
      <div style="flex: 1;width: 0;background-color: #ececed;padding: 10px">
        <RouterView />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { User } from '@element-plus/icons-vue';
import { reactive } from "vue";

// 处理 localStorage.getItem('xm-pro-user') 返回 null 的情况
const userData = localStorage.getItem('xm-pro-user');
const data = reactive({
  user: userData ? JSON.parse(userData) : null
});

const loginout = () => {
  localStorage.removeItem("xm-pro-user");//清除当前用户信息
  location.href = "/login";
};
const udpateUser = () => {
  const newUserData = localStorage.getItem("xm-pro-user");
  data.user = newUserData ? JSON.parse(newUserData) : null;
};
</script>
