import store from '@/store'
import axios from 'axios'
import { Message } from 'element-ui'
import util from '@/libs/util'
import router from '../../router'

// 使用vuex做全局loading时使用
// import store from '@/store'

// 创建一个错误
function errorCreat (msg) {
  const err = new Error(msg);
  errorLog(err);
  throw err
}

// 记录和显示错误
function errorLog (err) {
  // 添加到日志
  store.dispatch('d2admin/log/add', {
    type: 'error',
    err,
    info: '数据请求异常'
  });
  // 打印到控制台
  if (process.env.NODE_ENV === 'development') {
    util.log.danger('>>>>>> Error <<<<<<');
    console.log(err)
  }
  // 显示提示
  Message({
    message: err.message,
    type: 'error',
    duration: 5 * 1000
  })
}

// 创建 AXIOS 实例
const service = axios.create({
  baseURL: process.env.VUE_APP_API,
  headers: {
    'Content-Type': 'application/json;charset=utf8'
  },
  withCredentials: true,
  timeout: 3000,
  // 响应数据类型
  responseType: 'json',
  // 响应编码
  responseEncoding: 'utf8'
});

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 在请求发送之前做一些处理
    // 1. 请求开始的时候可以结合 vuex 开启全屏 loading 动画
    // console.log(store.state.loading)
    // console.log('准备发送请求...')
    
    if (!(/^https:\/\/|http:\/\//.test(config.url))) {
      const token = util.cookies.get('token');
      if (token && token !== 'undefined') {
        // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
        config.headers['token'] = token
      }
    }
    return config
  },
  
  error => {
    const errorUrl = error.url;
    const errorInfo = error.response;
    
    // 请求发送失败
    console.log('Request: ' + errorUrl, error);
    console.log(errorInfo);
    
    // 判断是否请求超时
    if (error.code === 'ECONNABORTED' && error.message.indexOf('timeout') !== -1) {
      errorCreat(`[ Request Timeout ] ${errorUrl} : ${errorInfo}`);
      console.log('Request Timeout', errorInfo);
    }
    
    // 错误页面重定向
    if (errorInfo) {
      const errorStatus = errorInfo.status; // 404 403 500 ...
      router.push({
        path: `/error/${errorStatus}`
      })
    }
    Promise.reject(error)
  }
);

// 响应拦截器
service.interceptors.response.use(
  response => {
    // dataAxios 是 axios 返回数据中的 data
    let dataAxios;
    // 浏览器IE9，响应为undefined获取相应信息
    if (response.data === undefined) {
      dataAxios = JSON.parse(response.request.responseText)
    } else {
      dataAxios = response.data
    }
    
    // 后端业务服务状态码
    const { code } = dataAxios;
    // 根据 code 进行先期响应码处理
    if (code === undefined) {
      // 如果没有 code 代表这不是项目后端开发的接口 比如可能是 D2Admin 请求最新版本
      return dataAxios
    } else {
      // 有 code 代表这是一个后端接口 可以进行进一步的判断
      switch (code) {
        case 200:    // 200 代表没有错误
          return dataAxios;
        case '999':  // 999 系统未知错误响应码
          errorCreat(`[ code: 999 ] ${dataAxios.msg}: ${response.config.url}`);
          break;
        default:     // 其他未定义的响应码
          errorCreat(`[ code: ${code} ] ${dataAxios.msg}: ${response.config.url}`);
          break;
      }
    }
  },
  error => {
    if (error && error.response) {
      switch (error.response.status) {
        case 400: error.message = '请求错误'; break;
        case 401: error.message = '未授权，请登录'; break;
        case 403: error.message = '拒绝访问'; break;
        case 404: error.message = `请求地址出错: ${error.response.config.url}`; break;
        case 408: error.message = '请求超时'; break;
        case 500: error.message = '服务器内部错误'; break;
        case 501: error.message = '服务未实现'; break;
        case 502: error.message = '网关错误'; break;
        case 503: error.message = '服务不可用'; break;
        case 504: error.message = '网关超时'; break;
        case 505: error.message = 'HTTP版本不受支持'; break;
        default: break
      }
    }
    errorLog(error);
    return Promise.reject(error)
  }
);

export default service
