export default {
  path: '',
  title: '持续集成',
  icon: 'usb',
  children: (pre => [
    {path: `${pre}serv`, title: '服务信息管理', icon: 'link'},
    {path: `${pre}build`, title: '构建信息管理', icon: 'history'},
    {path: `${pre}deploy`, title: '部署信息管理', icon: 'history'}
  ])('/cicd/')
}
