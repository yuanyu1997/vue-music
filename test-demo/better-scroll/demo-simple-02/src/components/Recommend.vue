<template>
  <!--轮播-->
  <div class="slider" ref="slider">
    <div class="slider-group" ref="sliderGroup">
      <!--插槽(图片)-->
      <slot></slot>
    </div>
    <div class="dots">
      <span class="dot" :class="{active: currentPageIndex === index }" v-for="(item, index) in dots"></span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import BScroll from 'better-scroll'
  import {addClass} from '../js/dom.js'

  export default {
    props: {
      // 是否循环播放
      loop: {
        type: Boolean,
        default: true
      },
      // 是否自动播放
      autoPlay: {
        type: Boolean,
        default: true
      },
      // 播放间隔
      interval: {
        type: Number,
        default: 3000
      }
    },
    data() {
      return {
        dots: [],
        // 当前播放的页数
        currentPageIndex: 0
      }
    },
    mounted() {
      setTimeout(() => {
        this._setSliderWidth()
        this._initDots()
        this._initSlider()

        if (this.autoPlay) {
          this._play()
        }
      }, 20) // 游览器默认刷新时间间隔为17ms

      // 当视口宽度改变需要重新计算slider
      window.addEventListener('resize', () => {
        if (!this.slider) {
          return
        }
        this._setSliderWidth(true)
        this.slider.refresh()
      })
    },
    // 销毁监听事件 手动清除timer
    destroyed() {
      clearTimeout(this.timer)
    },
    methods: {
      // 手动计算slider宽度
      _setSliderWidth(isResize = false) {
        this.children = this.$refs.sliderGroup.children

        // 总宽度
        let width = 0
        let sliderWidth = this.$refs.slider.clientWidth
        for (let i = 0; i < this.children.length; i++) {
          let child = this.children[i]
          addClass(child, 'slider-item')
          child.style.width = sliderWidth + 'px'
          width += sliderWidth
        }
        // 克隆(拷贝两份) 保证无缝滚动
        // 视口改变导致重新计算slider width不需要在拷贝了
        if (this.loop && !isResize) {
          width += 2 * sliderWidth
        }
        this.$refs.sliderGroup.style.width = width + 'px'
      },
      // 初始化slider
      _initSlider() {
        this.slider = new BScroll(this.$refs.slider, {
          // 横向滚动
          scrollX: true,
          // 纵向滚动
          scrollY: false,
          momentum: false,
          snap: true,
          // 是否循环轮播
          snapLoop: this.loop,
          snapThreshold: 0.3,
          snapSpeed: 400
        })

        // 绑定事件 维护currentPageIndex
        // 切换到下一章会派发一个scrollEnd时间
        this.slider.on('scrollEnd', () => {
          //alert(this.currentPageIndex)
          let pageIndex = this.slider.getCurrentPage().pageX
          if (this.loop) {
            pageIndex -= 1
          }
          this.currentPageIndex = pageIndex

          if (this.autoPlay) {
            clearTimeout(this.timer)
            this._play()
          }
        })
      },
      _initDots() {
        this.dots = new Array(this.children.length)
      },
      // 自动播放
      _play() {
        let pageIndex = this.currentPageIndex + 1
        if (this.loop) {
          pageIndex += 1
        }
        this.timer = setTimeout(() => {
          this.slider.goToPage(pageIndex, 0, 400)
        }, this.interval)
      }
    }
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">

  .slider
    min-height: 1px
    position: relative

    .slider-group
      position: relative
      overflow: hidden
      white-space: nowrap

      .slider-item
        float: left
        box-sizing: border-box
        overflow: hidden
        text-align: center

        a
          display: block
          width: 100%
          overflow: hidden
          text-decoration: none

        img
          display: block
          width: 100%

    .dots
      position: absolute
      right: 0
      left: 0
      bottom: 12px
      text-align: center
      font-size: 0

      .dot
        display: inline-block
        margin: 0 4px
        width: 8px
        height: 8px
        border-radius: 50%
        // background: rgba(255, 255, 255, 0.5)
        background: red

        &.active
          width: 20px
          border-radius: 5px
          // background: rgba(255, 255, 255, 0.8)
          background: yellow
</style>
