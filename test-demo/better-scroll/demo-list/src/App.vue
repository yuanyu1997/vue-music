<template>
  <div>
    <recommend :userList="userList"></recommend>
  </div>
</template>
<script>
  import axios from 'axios'
  import Recommend from './components/Recommend.vue'

  export default {
    data() {
      return {
        userList: []
      }
    },
    created() {
      this._getUserList()
    }, methods: {
      // 获取GitHub用户列表
      _getUserList() {
        const url = 'https://api.github.com/search/users?q=yuanyu'
        axios.get(url)
          .then(response => {
            const users = response.data.items
            for (let i in users) {
              const user = users[i]
              //[{userName: Xxx, photo: Xxx}...]
              this.userList.push({userName: user.login, photo: user.avatar_url})
            }
          })
      }
    },
    components: {
      Recommend
    }
  }
</script>
<style>
</style>
