'use strict'
import axios from 'axios'
import qs from 'qs'
// request拦截
axios.interceptors.request.use(config => {
    // loading
    // do something...
    return config
  }, error => {
    return Promise.reject(error)
})

//   response拦截
axios.interceptors.response.use(response => {
    // loading
    // do something...
    return response
}, error => {
    return Promise.resolve(error.response)
})

// 状态码 判断
function checkStatus (response) {
    // loading
    
    // 如果http状态码正常，则直接返回数据
    if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
      return response
      // 如果不需要除了data之外的数据，可以直接 return response.data
    }
    // 异常状态下，把错误信息返回去
    if(response.status == 404){
      alert('网络不佳，请检查网络状态')
    }
    return {
      status: -404,
      msg: 'mmp这个接口出事了'
    }
    // -----------
    return response
}

function checkCode (res) {
    
    // 如果code异常(这里已经包括网络错误，服务器错误，后端抛出的错误)，可以弹出一个错误提示，告诉用户
    // if (res.status === -404) {
    //   console.error(res.msg)
    // }
    // if (res.data && (!res.data.success)) {
    //   console.error(res.data.error_msg)
    // }
    return res
}

// var baseURL = 'http://192.168.31.34:8080/ManagementPlatform/'··`
// var baseURL = 'http://10.32.16.103:8080/ManagementPlatform/' 
// var baseURL = 'http://182.150.40.144:8084/ManagementPlatform/'
// var baseURL = 'http://scqbsoft.xicp.net:18080/ManagementPlatform/'
// var baseURL = 'http://yanjie.wicp.net:39246/ManagementPlatform/'
// var baseURL = 'http://10.32.16.2:18080/ManagementPlatform/' 
// var baseURL = 'http://192.168.31.133:8090/ManagementPlatform/' 
// var baseURL = 'http://10.32.16.106:18080/ManagementPlatform/'//本地

//眉山中医院外网

// var baseURL = 'http://171.211.195.250:9092/ManagementPlatform/'
// var baseURL = 'http://171.211.195.250:9093/ManagementPlatform/'
// var baseURL = 'http://110.189.154.18:8827/ManagementPlatform/'
// var baseURL = 'http://171.211.195.250:9093/ManagementPlatform/'
// var baseURL = 'http://110.189.154.18:8827/ManagementPlatform/'


var baseURL = 'http://192.168.200.201:8280/ManagementPlatform/'
//var baseURL = 'http://118.112.190.42:8280/ManagementPlatform/'

//var baseURL = 'http://118.119.104.198:8999/hcms/'
export default {
  baseURL,
  post (url, data) {
    return axios({
      method: 'post',
      baseURL: baseURL,  //如果 `url` 不是绝对地址，那么`baseURL`将会加到`url`的前面
      url,
      data: qs.stringify(data),
      timeout: 20000,
      headers: {              //请求头
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'token': sessionStorage.getItem('token')
      }
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  },
  get (url, params) {
    //console.log(params)
    params.dimOrganization = sessionStorage.getItem('jgid')
    return axios({
      method: 'get',
      baseURL: baseURL,
      url,
      params,         // 选项是要随请求一起发送的请求参数----一般链接在URL后面  
      timeout: 40000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'token': sessionStorage.getItem('token')
      }
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  },
  getNoParams (url, params) {
    return axios({
      method: 'get',
      baseURL: baseURL,
      url,
      params,         // 选项是要随请求一起发送的请求参数----一般链接在URL后面  
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'token': sessionStorage.getItem('token')
      }
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  },  
  postJson (url, data) {
    return axios({
      method: 'post',
      baseURL: baseURL,  //如果 `url` 不是绝对地址，那么`baseURL`将会加到`url`的前面
      url,
      data:  JSON.stringify(data),
      timeout: 20000,
      headers: {              //请求头
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json;charset=utf-8',
        'token': sessionStorage.getItem('token')
      }
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  }, 
  
}
  


