<template>
  <div class="singer" ref="singer">
    <list-view :similarSingerArr="similarSingerArr" ref="list"></list-view>
  </div>
</template>

<script type="text/ecmascript-6">
  import ListView from '../../base/listview/listview.vue'
  import {ERR_OK} from '../../api/config.js'
  import {getSingerList} from '../../api/singer.js'

  export default {
    data() {
      return {
        // 首字母相同的歌手组成的数组
        similarSingerArr: []
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
            this.similarSingerArr = res.data
          }
        })
      }
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
