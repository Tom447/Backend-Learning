import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system',
    name: 'system',
    meta: { title: '客户信息管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: '/customer',
        name: 'Customer',
        component: () => import('@/views/customer'),
        meta: { title: '客户管理', icon: 'el-icon-user-solid' }
      },
      {
        path: '/report',
        name: 'Report',
        component: () => import('@/views/report'),
        meta: { title: '信息统计', icon: 'el-icon-menu' }
      },
      {
        path: '/log',
        name: 'Log',
        component: () => import('@/views/log'),
        meta: { title: '日志查询', icon: 'el-icon-menu' }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
