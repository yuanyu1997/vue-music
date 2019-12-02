<!--轮播图-->
<template>
  <div class="slider" ref="slider">
    <div class="slider-group" ref="sliderGroup">
      <!--图片-->
      <slot></slot>
    </div>
  </div>
</template>

<script>
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
        default: 2000
      }
    },
    data() {
      return {
        // 当前播放的页数
        currentPageIndex: 0
      }
    },
    mounted: function () {
      setTimeout(() => {
        this._setSliderGroupWidth()
        this._initSlider()

        if (this.autoPlay) {
          this._play()
        }

      }, 20)

      // 当视口宽度改变需要重新计算slider
      window.addEventListener('resize', () => {
        if (!this.slider) {
          return
        }
        this._setSliderGroupWidth(true)
        this.slider.refresh()
      })


    },
    // 销毁监听事件 手动清除timer
    destroyed() {
      clearTimeout(this.timer)
    },
    methods: {
      _setSliderGroupWidth: function (isResize = false) {
        let sliderGroupWidth = 0
        let sliderWidth = this.$refs.slider.clientWidth
        this.children = this.$refs.sliderGroup.children
        for (let i = 0; i < this.children.length; i++) {
          let child = this.children[i]
          addClass(child, 'slider-item')
          child.style.width = sliderWidth + 'px'
          sliderGroupWidth += sliderWidth
        }
        // 克隆(拷贝两份) 保证无缝滚动
        // 视口改变导致重新计算slider width不需要在拷贝了
        if (this.loop && !isResize) {
          sliderGroupWidth += 2 * sliderWidth
        }
        this.$refs.sliderGroup.style.width = sliderGroupWidth + 'px'
      },
      _initSlider: function () {
        this.slider = new BScroll(this.$refs.slider, {
          scrollX: true,
          scrollY: false,
          momentum: false,
          snap: true,
          snapLoop: this.loop,
          snapThreshold: 0.3,
          snapSpeed: 400
        })

        // 绑定事件 维护currentPageIndex
        // 切换到下一章会派发一个scrollEnd事件
        this.slider.on('scrollEnd', () => {
          console.log('触发scrollEnd 事件 currentPageIndex=' + this.currentPageIndex)
          let pageIndex = this.slider.getCurrentPage().pageX
          // 循环播放
          if (this.loop) {
            pageIndex -= 1
          }
          this.currentPageIndex = pageIndex
          if (this.autoPlay) {
            // 清除定时器
            clearTimeout(this.timer)
            this._play()
          }
        })
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

    .slider-group
      position: relative
      overflow: hidden
      white-space: nowrap

      .slider-item
        float: left
        box-sizing: border-box
        overflow: hidden
        text-align: center

        img
          display: block
          width: 100%

</style>
