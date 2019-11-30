import {agentServer} from './config.js'
import musicData from '../common/js/music.js'

export function getRecommend() {
  // 轮播图
  const url = `${agentServer}/recommend/picture`
  return musicData(url)
}
