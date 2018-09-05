import request from '@/plugin/axios'

export function HTTP_ADD(url, data) {
  return request({
    method: 'post',
    url: url,
    data: data
  });
}

export function HTTP_EDIT(url, data) {
  return request({
    method: 'put',
    url: url,
    data: data
  });
}

export function HTTP_GET(url, data) {
  return request({
    method: 'get',
    url: url,
    data: data
  });
}

export function HTTP_DEL(url, data) {
  return request({
    method: 'delete',
    url: url,
    data: data
  });
}

export default {
  HTTP_ADD,
  HTTP_EDIT,
  HTTP_GET,
  HTTP_DEL
}