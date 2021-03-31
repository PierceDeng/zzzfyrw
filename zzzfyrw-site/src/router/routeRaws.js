

const index = () => import('@/pages/index/index.vue')
const login = () => import('@/pages/login/login.vue')



const routeRaws = [
    { path:'/', redirect: 'index',media:{title:'首页'}},
    { path: '/index', name: 'index',component: index, meta:{ isLogin: true, title: '首页'}},
    { path: '/login', name: 'login',component: login, meta:{ isLogin: false,title: '登录'}},
]


export default routeRaws;