import axios from 'axios'

const service = axios.create({
  baseURL: '/api',
  withCredentials: true,
  timeout: 500000 
})


// axios请求拦截器
service.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)


//axios响应拦截器
service.interceptors.response.use(res=>{
      return res;
    },
    
    error => {
      console.log('err' + error)
      let { message } = error;
      if (message == "Network Error") {
        message = "后端接口连接异常";
      }
      else if (message.includes("timeout")) {
        message = "系统接口请求超时";
      }
      else if (message.includes("Request failed with status code")) {
        message = "系统接口" + message.substr(message.length - 3) + "异常";
      }
      return Promise.reject(error)

})

export default service
