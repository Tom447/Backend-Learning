import request from '@/utils/request'

export function page(name,gender,phone,qq,page,pageSize) {
  return request({
    url: '/customers?name='+name+'&gender='+gender+'&phone='+phone+'&qq='+qq+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}

export function add(clazz) {
  return request({
    url: '/customers',
    method: 'post',
    data: clazz
  })
}

export function update(clazz) {
  return request({
    url: '/customers',
    method: 'put',
    data: clazz
  })
}


export function deleteById(ids) {
  return request({
    url: '/customers/'+ids,
    method: 'delete'
  })
}


export function selectById(id) {
  return request({
    url: '/customers/'+id,
    method: 'get'
  })
}

