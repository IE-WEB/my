# 🌐 后台管理系统（Spring Boot 3 + Vue 3）

这是一个前后端分离的后台管理系统，涵盖基础管理功能如用户登录、个人信息管理、富文本编辑、数据可视化展示及数据的批量新增与删除等。项目用于个人实战练习及技术展示，适合有一定 Java 基础的同学作为参考项目。

## 📌 项目简介

- 💻 前端基于 Vue 3 和 Element Plus 构建，界面简洁美观，交互友好；
- ⚙️ 后端采用 Spring Boot 3 提供高性能 RESTful API；
- 📊 使用 ECharts 实现动态数据可视化；
- 🗄️ 数据持久化由 MySQL 提供支持；
- 🚀 项目整体架构清晰，便于扩展与部署。

## 🧱 技术栈

| 层级     | 技术                      |
|----------|---------------------------|
| 前端     | Vue 3, Element Plus, Axios, Vite |
| 后端     | Spring Boot 3, Spring MVC |
| 数据库   | MySQL                     |
| 可视化   | ECharts                   |
| 其他工具 | Maven, Git, npm           |

## 📁 项目结构

后台管理系统/ ├── backend/ 后端 Spring Boot 项目 │ ├── src/ │ └── pom.xml ├── frontend/ 前端 Vue 3 项目 │ ├── src/ │ ├── public/ │ └── package.json ├── README.md 项目说明文件

## 🚀 快速启动指南

### ✅ 前置准备

确保你的开发环境已经安装以下工具：

- Node.js 16+
- JDK 17+
- MySQL 5.7/8.0
- Git

---

### 🛠️ 后端启动

1. 导入 `backend/` 到 IDEA；
2. 修改 `application.yml`，配置你的数据库信息；
3. 确保数据库已建好（如有 SQL 初始化文件请执行）；
4. 启动 Spring Boot 项目。

---

### 💻 前端启动

1. 进入 `frontend/` 目录；
2. 安装依赖：
   ```bash
   npm install
启动前端项目：

bash
复制
编辑
npm run dev
前端项目默认运行在 http://localhost:5173，后端接口运行在 http://localhost:8080。
### ✨ 核心功能展示
✅ 用户登录 / 退出

✅ 个人信息修改

✅ 富文本内容编辑（支持图片、格式、内容保存）

✅ 批量数据新增与删除

✅ 数据可视化图表（ECharts）

✅ 后台操作页面（使用 Element Plus 实现）

📸 项目界面截图（建议上传）
示例（建议你将运行截图截图后放在 GitHub 项目下的 assets/ 目录）：

登录页

信息管理页

富文本编辑页

图表展示页