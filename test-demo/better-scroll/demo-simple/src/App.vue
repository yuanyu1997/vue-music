<template>
  <div>
    <recommend>
      <div v-for="item in recommends">
        {{item.imgUrl}}
        <img :src="item.imgUrl">
      </div>
    </recommend>
  </div>
</template>

<script>
  import Recommend from './components/Recommend.vue'

  export default {
    data() {
      return {
        // 轮播图
        recommends: []
      }
    },
    created() {
      this._getRecommend()
    }, methods: {
      // 获取轮播图
      _getRecommend() {
        this.recommends = [{imgUrl: '../img/1.jpg'}, {imgUrl: './img/2.jpg'}, {imgUrl: './img/3.jpg'}, {imgUrl: './img/4.jpg'}]
      }
    },
    components: {
      Recommend
    }
  }
</script>

<style>

</style>
