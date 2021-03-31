import { createApp } from 'vue'
import App from './App.vue'
import router from "./router/router"

/**
 * 第三方组件
 */
//element-ui
import installElementPlus from './plugins/element'



/**
 * 引入外部css
 * @type {App<Element>}
 */
import './scss/common.scss'
import 'nprogress/nprogress.css'; //网页加载进度条

const app = createApp(App)
installElementPlus(app)
app.use(router)
app.mount('#app');