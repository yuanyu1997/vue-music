<template>
  <ul class="listview">
    <li v-for="item in userList" class="list-group" ref="listGroup">
      <uL>
        <li class="list-group-item">
          <!--头像-->
          <img class="avatar" :src="item.photo">
          <!--用户名-->
          <span class="name">{{item.userName}}</span>
        </li>
      </uL>
    </li>
  </ul>
</template>

<script>
  export default {
    props: {
      userList: {
        type: Array
      },
    },
    mounted: function () {
    },
    methods: {}
  }
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
  @import "../stylus/variable.styl"
  .listview
    position: relative
    width: 100%
    height: 100%
    overflow: hidden
    background: $color-background

    .list-group
      padding-bottom: 30px

      .list-group-title
        height: 30px
        line-height: 30px
        padding-left: 20px
        font-size: $font-size-small
        color: $color-text-l
        background: $color-highlight-background

      .list-group-item
        display: flex
        align-items: center
        padding: 20px 0 0 30px

        .avatar
          width: 50px
          height: 50px
          border-radius: 50%

        .name
          margin-left: 20px
          color: $color-text-l
          font-size: $font-size-medium

    .list-shortcut
      position: absolute
      z-index: 30
      right: 0
      top: 50%
      transform: translateY(-50%)
      width: 20px
      padding: 20px 0
      border-radius: 10px
      text-align: center
      background: $color-background-d
      font-family: Helvetica

      .item
        padding: 3px
        line-height: 1
        color: $color-text-l
        font-size: $font-size-small

        &.current
          color: $color-theme

    .list-fixed
      position: absolute
      top: 0
      left: 0
      width: 100%

      .fixed-title
        height: 30px
        line-height: 30px
        padding-left: 20px
        font-size: $font-size-small
        color: $color-text-l
        background: $color-highlight-background

    .loading-container
      position: absolute
      width: 100%
      top: 50%
      transform: translateY(-50%)
</style>
