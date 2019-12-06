// 获取歌手信息
import musicData from './music.js'
import {agentServer} from './config.js'

// "key"相同的歌手组成的数组(首字母|热门)
export function getSingerList() {
  const url = `${agentServer}/singer/list`
  return musicData(url)
}

// 获取指定歌手的歌曲信息
export function getSingerDetail(singerId) {
  const url = `${agentServer}/singer/detail/${singerId}`
  return musicData(url)
}
