import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function getInfo(token) {
  return request({
    url: `/api/user/info`,
    method: 'get',
    params: { token }
  })
}


