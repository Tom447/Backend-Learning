import request from '@/utils/request'

export function page(name, no, degree, clazzId, page, pageSize) {
  return request({
    url: '/students?name=' + name + '&no=' + no + '&degree=' + degree + '&classId=' + clazzId + '&page=' + page + '&pageSize=' + pageSize,
    method: 'get'
  })
}

export function add(student) {
  return request({
    url: '/students',
    method: 'post',
    data: student
  })
}

export function update(student) {
  return request({
    url: '/students',
    method: 'put',
    data: student
  })
}

export function deleteById(id) {
  return request({
    url: '/students/' + id,
    method: 'delete'
  })
}

export function selectById(id) {
  return request({
    url: '/students/' + id,
    method: 'get'
  })
}



export function violation(id, score) {
  return request({
    url: '/students/violation/' + id + '/' + score,
    method: 'put'
  })
}
