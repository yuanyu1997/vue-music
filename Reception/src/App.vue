<template>
  <div id="app">
    <!--header组件-->
    <m-header></m-header>
    <!--顶导tab组件-->
    <tab></tab>
    <!--路由-->
    <router-view></router-view>
  </div>
</template>

<script type="text/ecmascript-6">
  import MHeader from './components/m-header/m-header.vue'
  import Tab from './components/tab/tab.vue'

  export default {
    components: {
      MHeader,
      Tab
    }
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
</style>
