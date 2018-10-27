export default {
  path: '',
  title: '持续集成',
  icon: 'usb',
  children: (pre => [
    {path: `${pre}serv`, title: '服务信息管理', icon: 'link'}
  ])('/cicd/')
}
