/**
 * 接口信息定义
 */

export const API_DICT_DATABASE = {
  URL_BASE: '/db',
  URL_BENCHMARK: '/db/benchmark',
  DATABASE: {
    id: 0,
    env: '',
    eng: '',
    chs: '',
    benchmark: 0,
    type: '',
    driver: '',
    url: '',
    account: '',
    password: '',
    creater: '',
    ctime: '',
    updater: '',
    utime: '',
    note: ''
  }
};

export const API_DICT_COMPARE = {
  URL_BASE: '/db/compare',
  QUERY: {
    eng: '',
    env: ''
  }
};

export default {
  API_DICT_DATABASE,
  API_DICT_COMPARE
}