import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Students.vue'
import SecurityLogin from "@/views/SecurityLogin";
import Register from "@/views/Register";
import PersonInformation from "@/views/PersonInformation";
import Users from "@/views/Users";
import Students from "@/views/Students";

Vue.use(VueRouter)

// // 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
// const originalPush = VueRouter.prototype.push
// VueRouter.prototype.push = function push(location) {
//   return originalPush.call(this, location).catch(err => err)
// }

const originalReplace = VueRouter.prototype.replace;
VueRouter.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch(err => err);
};


const routes = [
  {
    path: '/home',
    name: 'home',
    component: Students
  },

  {
    path: '/security',
    name: 'SecurityLogin',
    component: SecurityLogin
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/person',
    name: 'PersonInformation',
    component: PersonInformation
  },
  {
    path: '/usersmanage',
    name: 'Users',
    component: Users
  },
  {
      path: '/',
      redirect: '/security',//设置默认指向的路径
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
      routes
},

)

export default router
// export default new VueRouter({
//   routes: [
//       {
//     path: '/',
//     redirect: '/security',//设置默认指向的路径
//     name: 'SecurityLogin'
//   },
//     {
//     path: '/security',//设置默认指向的路径指向的是下面的MyHome组件
//     component: SecurityLogin,
//     name: 'SecurityLogin'
//   }]
// })
