import {agentServer} from './config.js'
import musicData from './music.js'

/**
 * 获取轮播图
 */
export function getRecommend() {
  // 轮播图
  const url = `${agentServer}/recommend/picture`
  return musicData(url)
}

/**
 * 获取歌单
 */
export function getDiscList() {
  // 歌单
  const url = `${agentServer}/recommend/songList`
  return musicData(url)
}
