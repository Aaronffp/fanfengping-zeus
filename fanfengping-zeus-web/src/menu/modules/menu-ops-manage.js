export default {
  path: '',
  title: '运维管理',
  icon: 'linux',
  children: (pre => [
    {path: `${pre}apps`, title: '应用管理', icon: 'barcode'},
    {path: `${pre}serv`, title: '服务管理', icon: 'qrcode'},
    {path: `${pre}env`, title: '环境管理', icon: 'exchange'},
    {path: `${pre}moni`, title: '监控管理', icon: 'exchange'}
  ])('/ops/')
}
