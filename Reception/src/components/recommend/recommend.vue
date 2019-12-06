<!--推荐-->
<template>
  <div class="recommend">
    <scroll ref="scroll" class="recommend-content" :data="discList">
      <div>
        <!--拿到数据才渲染(异步函数)-->
        <div v-if="recommends.length" class="slider-wrapper">
          <!--轮播图-->
          <slider>
            <div v-for="item in recommends">
              <!--:href="item.linkUrl"-->
              <a href="javascript:void(0)">
                <!--fastclick对class为needsclick属性不会拦截点击-->
                <!--在img标签的src属性填充上值之后，触发onload事件-->
                <img class="needsclick" @load="loadImage" :src="item.imgUrl" alt="全力抢修中...">
              </a>
            </div>
          </slider>
        </div>
        <div class="recommend-list">
          <h1 class="list-title">热门歌单推荐</h1>
          <ul>
            <li v-for="item in discList" class="item">
              <div class="icon">
                <img width="60" height="60" v-lazy="item.imgurl">
              </div>
              <div class="text">
                <h2 class="name" v-html="item.name"></h2>
                <p class="desc" v-html="item.desc"></p>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="loading-container" v-show="!discList.length">
        <loading></loading>
      </div>
    </scroll>
  </div>
</template>

<script type="text/ecmascript-6">
  import Loading from '../../base/loading/loading'
  import Scroll from '../../base/scroll/scroll'
  import {ERR_OK} from '../../api/config.js'
  import Slider from '../../base/slider/slider.vue'
  import {getRecommend, getDiscList} from '../../api/recommend.js'

  export default {
    data() {
      return {
        // 推荐(轮播图)
        recommends: [],
        // 歌单
        discList: []
      }
    },
    created() {
      this._getRecommend()
      this._getDiscList()
    },
    methods: {
      // 获取推荐(轮播图)
      _getRecommend() {
        getRecommend().then((res) => {
          if (res.code === ERR_OK) {
            this.recommends = res.data
          }
        })
      },
      // 获取歌单
      _getDiscList() {
        getDiscList().then((res) => {
          if (res.code === ERR_OK) {
            this.discList = res.data
          }
        })
      },
      // 只要有一张图片撑开了轮播图 就重新计算scroll
      loadImage() {
        if (!this.checkloaded) {
          this.checkloaded = true
          this.$refs.scroll.refresh()
        }
      }
    },
    components: {
      Slider,
      Scroll,
      Loading
    }
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/variable.styl"

  .recommend
    position: fixed
    width: 100%
    top: 88px
    bottom: 0

    .recommend-content
      height: 100%
      overflow: hidden

      .slider-wrapper
        position: relative
        width: 100%
        overflow: hidden

      .recommend-list
        .list-title
          height: 65px
          line-height: 65px
          text-align: center
          font-size: $font-size-medium
          color: $color-theme

        .item
          display: flex
          box-sizing: border-box
          align-items: center
          padding: 0 20px 20px 20px

          .icon
            flex: 0 0 60px
            width: 60px
            padding-right: 20px

          .text
            display: flex
            flex-direction: column
            justify-content: center
            flex: 1
            line-height: 20px
            overflow: hidden
            font-size: $font-size-medium

            .name
              margin-bottom: 10px
              color: $color-text

            .desc
              color: $color-text-d

      .loading-container
        position: absolute
        width: 100%
        top: 50%
        transform: translateY(-50%)
</style>
