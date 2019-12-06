import * as types from './mutation-types.js'

const matutaions = {
  // 修改singer
  [types.SET_SINGER](state, singer) {
    state.singer = singer
  }
}
export default matutaions
