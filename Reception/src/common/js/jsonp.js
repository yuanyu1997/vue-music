import originJsonp from 'jsonp'

/**
 * @param url 纯净的地址
 * @param data 请求参数
 * @param option
 */
export default function jsonp(url, data, option) {
  // 拼接请求参数
  url += (url.indexOf('?') < 0 ? '?' : '&') + param(data)

  return new Promise((resolve, reject) => {
    originJsonp(url, option, (err, data) => {
      if (!err) { // 成功
        resolve(data)
      } else { // 失败
        reject(err)
      }
    })
  })
}

/**
 * 拼接请求参数
 * @param data 请求参数
 */
export function param(data) {
  let url = ''
  for (const k in data) {
    let value = data[k] !== undefined ? data[k] : ''
    url += '&' + k + '=' + encodeURIComponent(value)
  }
  // 第一个&需要去掉
  return url ? url.substring(1) : ''
}
