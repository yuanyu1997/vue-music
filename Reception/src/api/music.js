// 获取音乐资源
import axios from 'axios'
export default function musicData(url) {
  return new Promise((resolve, reject) => {
    axios.get(url).then(success => {
      resolve(success.data)
    }).catch(error => {
      reject(error)
    })
  })
}
