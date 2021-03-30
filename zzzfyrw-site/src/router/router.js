import { createRouter, createWebHistory } from 'vue-router'
import NProgress from "nprogress";

import index from '../pages/index/index.vue'
import login from '../pages/login/login.vue'

const routerHistory = createWebHistory()

const router = createRouter({
    history: routerHistory,
    routes: [
        {
            path: '/index',
            component: index,
            meta:{
                name: 'index',
                isLogin: true,
            },
        },
        {
            path: '/login',
            component: login,
            meta:{name: 'login', isLogin: false,},
        },
        {
            path: '/',
            component: index,
            redirect: '/index',
            meta:{
                name: 'index',
                isLogin: true,},
        },
    ],
    hashbang: true,
    mode: 'hash',
    transitionOnLoad: true,
});

router.beforeEach((to,from,next) => {
    // to: Route: 即将要进入的目标 路由对象
    // from: Route: 当前导航正要离开的路由
    // next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
    NProgress.start();
    if (to.name === 'login') {
        next();
    } else {
        if (to.meta.isLogin) {
            if (!localStorage.getItem('token') || !localStorage.getItem('userInfo')) {
                next({path: '/login'});
            } else {
                next()
            }
        } else {
            next();
        }
    }
})

router.afterEach(function () {
    NProgress.done();
    window.scrollTo(0, 0);
});



export default router
