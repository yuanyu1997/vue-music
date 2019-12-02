// 获取歌手信息
import musicData from './music.js'
import {agentServer} from './config.js'
// 获取歌手列表
export function getSingerList() {
  const url = `${agentServer}/singer/list`
  return musicData(url)
}

// 获取歌手详细信息
export function getSingerDetail(singerId) {
  const url = `${agentServer}/singer/list`
  return musicData(url)
}
