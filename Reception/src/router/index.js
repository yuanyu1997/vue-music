import Vue from 'vue'
import Router from 'vue-router'

import Recommend from '../components/recommend/recommend.vue'
import Singer from '../components/singer/singer.vue'
import Rank from '../components/rank/rank.vue'
import Search from '../components/search/search.vue'

import SingerDetail from '../components/singer-detail/singer-detail.vue'

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/recommend'
    },
    {
      // 推荐页面
      path: '/recommend',
      component: Recommend
    },
    {
      // 歌手页面
      path: '/singer',
      component: Singer,
      children: [
        {
          path: ':id',
          component: SingerDetail
        }
      ]
    },
    {
      // 排行页面
      path: '/rank',
      component: Rank
    },
    {
      // 搜索页面
      path: '/search',
      component: Search
    }
  ]
})
