import request from '@/utils/request'

export function getAgeData() {
  return request({
    url: '/customers/ageReport',
    method: 'get'
  })
}


