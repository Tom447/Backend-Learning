import request from '@/utils/request'

export function page(name, begin, end, page, pageSize) {
  return request({
    url: '/clazzs?name=' + name + '&begin=' + begin + '&end=' + end + '&page=' + page + '&pageSize=' + pageSize,
    method: 'get'
  })
}

export function add(clazz) {
  return request({
    url: '/clazzs',
    method: 'post',
    data: clazz
  })
}

export function update(clazz) {
  return request({
    url: '/clazzs',
    method: 'put',
    data: clazz
  })
}

export function deleteById(id) {
  return request({
    url: '/clazzs/' + id,
    method: 'delete'
  })
}

export function selectById(id) {
  return request({
    url: '/clazzs/' + id,
    method: 'get'
  })
}

export function findAll() {
  return request({
    url: '/clazzs/list',
    method: 'get'
  })
}
