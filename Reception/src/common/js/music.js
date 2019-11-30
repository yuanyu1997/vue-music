import axios from 'axios'
// 获取音乐资源
export default function musicData(url) {
  return new Promise((resolve, reject) => {
    axios.get(url).then(success => {
      resolve(success.data)
    }).catch(error => {
      reject(error)
    })
  })
}
