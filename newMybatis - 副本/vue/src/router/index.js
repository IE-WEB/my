import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: "/" ,redirect:"/login"},
    {
      path: '/Manager', meta: {title:"网站"} ,name: 'ma', component: () => import('../views/Mananger.vue'),children:[
        {path: 'Home',meta: {title:"主页"} , name: 'home', component: () => import('../views/Home.vue'),},
        {path: 'test', meta: {title:"跳转页面"} ,name: 'test', component: () => import('../views/Test.vue'),},
        {path: 'data', meta: {title:"数据页面"} ,name: 'data', component: () => import('../views/Data.vue')},
        {path: 'show', meta: {title:"数据展示页面"} ,name: 'SHOW', component: () => import('../views/show.vue')},
        {path: 'employee', meta: {title:"员工信息"} ,name: 'person', component: () => import('../views/Employee.vue')},
        {path: 'admin', meta: {title:"管理员信息"} ,name: 'admin', component: () => import('../views/Admin.vue')},
        {path: 'person', meta: {title:"个人信息"} ,name: 'ONE', component: () => import('../views/Person.vue')},
        {path: 'password', meta: {title:"个人密码"} ,name: 'password', component: () => import('../views/Password.vue')},
        {path: 'article', meta: {title:"文章"} ,name: 'article', component: () => import('../views/Article.vue')},
        {path: 'department', meta: {title:"部门"} ,name: 'department', component: () => import('../views/Department.vue')},
      ]
    },
    {path: '/login', meta: {title:"登录"}, component: () => import('../views/Login.vue'),},
    {path: '/register', meta: {title:"注册"}, component: () => import('../views/Register.vue'),},
    {path: '/404', meta: {title:"not found"} ,name: '404', component: () => import('../views/404.vue'),},
    {path: '/:pathMath(.*)',redirect: '/404'}

  ],
})
router.beforeEach((to,from,next )=>{
    document.title= to.meta.title;
    next();
})


export default router
