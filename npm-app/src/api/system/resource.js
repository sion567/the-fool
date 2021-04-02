import request from '@/utils/request'

export function getUserResources() {
    return request({
      url: '/api/resources',
      method: 'get'
    })
  }