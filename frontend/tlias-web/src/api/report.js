import request from '@/utils/request'

export function getGenderData() {
  return request({
    url: '/report/empGenderData',
    method: 'get'
  })
}

export function getJobData() {
  return request({
    url: '/report/empJobData',
    method: 'get'
  })
}

export function getStudentData() {
  return request({
    url: '/report/studentData',
    method: 'get'
  })
}
