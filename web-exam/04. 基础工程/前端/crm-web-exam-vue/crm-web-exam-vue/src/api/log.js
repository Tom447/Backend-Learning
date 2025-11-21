import request from '@/utils/request'

export function page(page,pageSize) {
  return request({
    url: '/logs?page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}
