import 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import router from './router'
import 'common/stylus/index.styl'

import fastclick from 'fastclick'
// fastclick解决移动端延时问题
fastclick.attach(document.body)
/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
