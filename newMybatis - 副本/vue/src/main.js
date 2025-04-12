

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import "@/assets/global.css"
import zhCn from 'element-plus/es/locale/lang/zh-cn'
const app = createApp(App)
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

app.use(router)

app.use(ElementPlus,{
    locale: zhCn,
})
app.mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}