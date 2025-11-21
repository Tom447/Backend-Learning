import request from '@/utils/request'

export function page(name,no,degree,clazzId,page,pageSize) {
  return request({
    url: '/students?name='+name+'&no='+no+'&degree='+degree+'&clazzId='+clazzId+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}

export function add(stu) {
  return request({
    url: '/students',
    method: 'post',
    data: stu
  })
}

export function update(stu) {
  return request({
    url: '/students',
    method: 'put',
    data: stu
  })
}


export function deleteById(id) {
  return request({
    url: '/students/'+id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/students/'+id,
    method: 'get',
  })
}


export function violationHandle(id, score) {
  return request({
    url: '/students/violation/'+id+"/"+score,
    method: 'put'
  })
}