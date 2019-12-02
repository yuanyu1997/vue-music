<!--轮播图(基本功能)-->
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
      }
    },
    mounted: function () {
      setTimeout(() => {
        this._setSliderGroupWidth()
        this._initSlider()
      }, 20)// 游览器默认刷新时间间隔为17ms
    },
    methods: {
      // 计算sliderGroup的宽度
      _setSliderGroupWidth: function () {
        // 计算宽度  = 图片个数+每张图片的宽度
        let sliderGroupWidth = 0
        // sliderWidth = width+左右padding
        let sliderWidth = this.$refs.slider.clientWidth

        // 获取sliderGroup里的所有的子元素
        this.children = this.$refs.sliderGroup.children

        for (let i = 0; i < this.children.length; i++) {
          // 获取children里的每一项内容
          let child = this.children[i]
          //console.log(child)
          addClass(child, 'slider-item')
          child.style.width = sliderWidth + 'px'
          sliderGroupWidth += sliderWidth
        }
        // 克隆(拷贝两份) 保证无缝滚动
        if (this.loop) {
          sliderGroupWidth += 2 * sliderWidth
        }
        this.$refs.sliderGroup.style.width = sliderGroupWidth + 'px'
      },
      // 设置sliderGroup宽度以后初始化slider
      _initSlider: function () {
        this.slider = new BScroll(this.$refs.slider, {
          // 横向滚动
          scrollX: true,
          // 纵向滚动
          scrollY: false,
          momentum: false,
          // snap: {
          //   loop: this.loop,
          //   threshold: 0.3,
          //   speed: 400
          // }
          snap: true,
          // 是否循环轮播
          snapLoop: this.loop,
          snapThreshold: 0.3,
          snapSpeed: 400
        })
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
