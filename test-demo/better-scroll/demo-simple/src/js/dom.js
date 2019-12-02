// dom操作通用代码
/**
 * dom对象是否包含指定的className
 * @param el dom对象
 * @param className
 */
export function hasClass(el, className) {
  let reg = new RegExp('(^|\\s)' + className + '(\\s|$)')
  return reg.test(el.className)
}

/**
 * 给dom添加一个className
 * @param el dom对象
 * @param className className
 */
export function addClass(el, className) {
  if (hasClass(el, className)) {
    return
  }
  // 添加className
  let newClass = el.className.split(' ')
  newClass.push(className)
  el.className = newClass.join(' ')
}
