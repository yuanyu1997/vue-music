import Vue from 'vue'
import Router from 'vue-router'

import Recommend from 'components/recommend/recommend'
import Singer from 'components/singer/singer'
import Rank from 'components/rank/rank'
import Search from 'components/search/search'

Vue.use(Router)
export default new Router({
  routes: [
    {
      // 高亮
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
      component: Singer
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
