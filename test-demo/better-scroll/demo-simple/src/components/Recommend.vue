<template>
  <div class="slider" ref="slider">
    <div class="slider-content" ref="sliderContent">
      <!--轮播图-->
      <slot></slot>
    </div>
  </div>
</template>

<script>
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
    mounted: function () {
      setTimeout(() => {
        this.setSliderWidth()
        this.initSlider()
      }, 20)
    },
    methods: {
      // 设置slider的宽度
      setSliderWidth: function (isResize) {
        // 获取slider里的所有的子元素
        this.children = this.$refs.sliderContent.children
        // console.log(this.children)
        // 计算宽度  = 图片个数+每张图片的宽度
        let width = 0
        // 获取手机屏幕的宽度
        let sliderWidth = this.$refs.slider.clientWidth

        for (let i = 0; i < this.children.length; i++) {
          // 获取children里的每一项内容
          let child = this.children[i]

          child.style.width = sliderWidth + 'px'
          width += sliderWidth
        }
        if (this.loop) {
          width += 2 * sliderWidth
        }
        this.$refs.sliderContent.style.width = width + 'px'
      },
      // 设置宽度以后初始化slider
      initSlider: function () {
        this.slider = new BScroll(this.$refs.slider, {
          scrollX: true,
          scrollY: false,
          momentum: false,
          snap: {
            loop: this.loop,
            threshold: 0.3,
            speed: 400
          },
          click: true
        })
      }

    }
  }
</script>


<style>

</style>
