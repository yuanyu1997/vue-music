<template>
  <div>
    <recommend>
      <div v-for="item in recommends">
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
        // 轮播图Url
        recommends: []
      }
    },
    created() {
      this._getRecommend()
    }, methods: {
      // 获取轮播图Url
      _getRecommend() {
        this.recommends = [
          {imgUrl: 'https://img-blog.csdnimg.cn/20191202123638931.jpg'},
          {imgUrl: 'https://img-blog.csdnimg.cn/20191202123638960.jpg'},
          {imgUrl: 'https://img-blog.csdnimg.cn/20191202123638991.jpg'},
          {imgUrl: 'https://img-blog.csdnimg.cn/2019120212363924.jpg'},
          {imgUrl: 'https://img-blog.csdnimg.cn/20191202123635862.jpg'}]
      }
    },
    components: {
      Recommend
    }
  }
</script>
<style>
</style>
