export default {
  path: '',
  title: '自动化',
  icon: 'spinner',
  children: (pre => [
    {path: `${pre}mock`, title: '挡板管理', icon: 'puzzle-piece'},
    {path: `${pre}api`, title: '接口测试', icon: 'plug'},
    {path: `${pre}perf`, title: '性能测试', icon: 'thermometer'},
    {path: `${pre}jmx`, title: 'JMeter测试', icon: 'gg'},
    {path: `${pre}book`, title: 'WEB测试', icon: 'chrome'},
    {path: `${pre}app`, title: 'APP测试', icon: 'android'},
    {path: `${pre}desk`, title: '客户端测试', icon: 'desktop'}
  ])('/auto/')
}
