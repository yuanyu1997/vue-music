<template>
  <div class="singer" ref="singer">
    <list-view @select="selectSinger" :data="singers" ref="list"></list-view>
    <router-view></router-view>
  </div>
</template>

<script type="text/ecmascript-6">
  import ListView from '../../base/listview/listview.vue'
  import {ERR_OK} from '../../api/config.js'
  import {getSingerList} from '../../api/singer.js'

  import {mapMutations} from 'vuex'

  export default {
    data() {
      return {
        // 首字母相同的歌手组成的数组
        singers: []
      }
    },
    created() {
      this._getSingerList()
    },
    methods: {
      _getSingerList() {
        getSingerList().then((res) => {
          if (res.code === ERR_OK) {
            // 热门 A B C ... Z
            this.singers = res.data
          }
        })
      },
      // 跳转到对应歌手详情页
      selectSinger(singer) {
        this.$router.push({
          path: `/singer/${singer.id}`
        })
        this.setSinger(singer)
      },
      ...mapMutations({
        setSinger: 'SET_SINGER'
      })
    },
    components: {
      ListView
    }
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
  .singer
    // 固定父容器的高度better-scroll滚动需要
    position: fixed
    top: 88px
    bottom: 0
    width: 100%
</style>
