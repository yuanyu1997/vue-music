<!--轮播-->
<template>
  <div class="slider" ref="slider">
    <div class="slider-group" ref="sliderGroup">
      <slot></slot>
    </div>
    <div class="dots">
      <span class="dot" :class="{active: currentPageIndex === index }" v-for="(item, index) in dots"></span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import BScroll from 'better-scroll'
  import {addClass} from '../../common/js/dom.js'

  export default {
    // name: 'slider',
    props: {
      // 是否循环轮播
      loop: {
        type: Boolean,
        default: true
      },
      // 是否自动轮播
      autoPlay: {
        type: Boolean,
        default: true
      },
      // 自动轮播时间间隔
      interval: {
        type: Number,
        default: 4000
      }
    },
    data() {
      return {
        dots: [],
        // 当前是第几页
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
      // 手动计算sliderGroup宽度(横向滚动，不能被自动撑开)
      _setSliderWidth(isResize = false) {
        // 总宽度
        let width = 0
        // 外层slider宽度（这里其实就是设备宽度）
        let sliderWidth = this.$refs.slider.clientWidth

        this.children = this.$refs.sliderGroup.children
        for (let i = 0; i < this.children.length; i++) {
          let child = this.children[i]
          // child的class属性添加一个slider-item值
          addClass(child, 'slider-item')
          child.style.width = sliderWidth + 'px'
          width += sliderWidth
        }
        // 拷贝两份 保证无缝滚动
        // 视口改变导致重新计算sliderGroup不需要在拷贝了
        if (this.loop && !isResize) {
          width += 2 * sliderWidth
        }
        this.$refs.sliderGroup.style.width = width + 'px'
      },
      // 初始化slider
      _initSlider() {
        this.slider = new BScroll(this.$refs.slider, {
          // true 滚动方向为X轴
          scrollX: true,
          // true 滚动方向为Y轴
          scrollY: false,
          // true 当快速滚动时是否开启滑动惯性
          momentum: false,
          // false 改属性是给slider组件使用的，普通的列表滚动不需要配置
          snap: true,
          // false 是否可以无缝循环滚动
          snapLoop: this.loop,
          // 0.1 用手指滑动时页面可切换的阈值，大于这个阈值可以滑动到下一页
          snapThreshold: 0.3,
          // 400 轮播图切换的动画时间
          snapSpeed: 400
          // true 是否派发clink时间
          // better-scroll会阻止游览器默认的clink然后自己派发一个clink
          // fastclick也会阻止better-scroll派发的clink事件
          // clink: true
        })

        // 绑定事件 维护currentPageIndex
        // better-scroll在滚动时会派发一个scrollEnd时间
        this.slider.on('scrollEnd', () => {
          let pageIndex = this.slider.getCurrentPage().pageX
          if (this.loop) {
            pageIndex -= 1
          }
          this.currentPageIndex = pageIndex

          if (this.autoPlay) {
            // 有手动拖放和自动播放 需要清除timer
            clearTimeout(this.timer)
            this._play()
          }
        })
      },
      // 一个空数组
      _initDots() {
        this.dots = new Array(this.children.length)
      },
      // 跳转到下一张图片
      _play() {
        let pageIndex = this.currentPageIndex + 1
        if (this.loop) {
          pageIndex += 1
        }
        this.timer = setTimeout(() => {
          // 横向轮播
          this.slider.goToPage(pageIndex, 0, 400)
        }, this.interval)
      }
    }
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/variable.styl"

  .slider
    min-height: 1px

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
        background: $color-text-l

        &.active
          width: 20px
          border-radius: 5px
          background: $color-text-ll
</style>
