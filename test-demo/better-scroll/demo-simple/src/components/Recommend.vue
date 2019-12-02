<!--基本的轮播图-->
<template>
  <div class="wrapper" ref="wrapper">
    <div class="content" ref="content">
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
        this.setSliderWidth()
        this.initSlider()
      }, 20)
    },
    methods: {
      // 计算content的宽度
      setSliderWidth: function () {
        // 获取content里的所有的子元素
        this.children = this.$refs.content.children
        // 计算宽度  = 图片个数+每张图片的宽度
        let width = 0

        // wrapperWidth = width+左右padding
        let wrapperWidth = this.$refs.wrapper.clientWidth

        for (let i = 0; i < this.children.length; i++) {
          // 获取children里的每一项内容
          let child = this.children[i]
          addClass(child, 'content-item')
          child.style.width = wrapperWidth + 'px'
          width += wrapperWidth
        }
        if (this.loop) {
          width += 2 * wrapperWidth
        }
        this.$refs.content.style.width = width + 'px'
      },
      // 设置宽度以后初始化wrapper
      initSlider: function () {
        this.wrapper = new BScroll(this.$refs.wrapper, {
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
      }

    }
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
  .wrapper
    min-height: 1px

    .content
      position: relative
      overflow: hidden
      white-space: nowrap

      .content-item
        float: left
        box-sizing: border-box
        overflow: hidden
        text-align: center

        img
          display: block
          width: 100%
</style>
