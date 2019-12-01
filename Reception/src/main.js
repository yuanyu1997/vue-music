// babel-polyfill用于将ES6代码转为ES5代码
import 'babel-polyfill'

import Vue from 'vue'
import App from './App'

import router from './router/index.js'

import './common/stylus/index.styl'

// fastclick解决移动端延时问题
import fastclick from 'fastclick'

// 图片懒加载
import VueLazyload from 'vue-lazyload'

Vue.use(VueLazyload, {
  loading: require('./common/image/default.png')
})

fastclick.attach(document.body)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
