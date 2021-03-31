import { createRouter, createWebHistory } from 'vue-router'
import NProgress from "nprogress";
import routeRaws from "./routeRaws";

const routerHistory = createWebHistory()


const router = createRouter({
    history: routerHistory,
    routes: routeRaws,
    scrollBehavior: scrollBehavior
});

router.beforeEach((to,from,next) => {
    // to: Route: 即将要进入的目标 路由对象
    // from: Route: 当前导航正要离开的路由
    // next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
    NProgress.start();

    console.info(to);
    if(to.fullPath == '/'){
        // next({path:'/login',replace:true})
    }else {
        next();
    }
})

router.afterEach(function () {
    NProgress.done();
});


function scrollBehavior(to,from,position){
    window.scrollTo(0, 0);
}


export default router
