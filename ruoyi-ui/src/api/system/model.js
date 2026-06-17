import request from '@/utils/request'

// 查询模型数据列列表
export function listMODEL(query) {
  return request({
    url: '/system/MODEL/list',
    method: 'get',
    params: query
  })
}

// 查询模型数据列详细
export function getMODEL(ID) {
  return request({
    url: '/system/MODEL/' + ID,
    method: 'get'
  })
}

// 新增模型数据列
export function addMODEL(data) {
  return request({
    url: '/system/MODEL',
    method: 'post',
    data: data
  })
}

// 修改模型数据列
export function updateMODEL(data) {
  return request({
    url: '/system/MODEL',
    method: 'put',
    data: data
  })
}

// 删除模型数据列
export function delMODEL(ID) {
  return request({
    url: '/system/MODEL/' + ID,
    method: 'delete'
  })
}
