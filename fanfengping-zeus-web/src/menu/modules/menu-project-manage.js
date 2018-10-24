export default {
  path: '',
  title: '项目管理',
  icon: 'flag',
  children: (pre => [
    {path: `${pre}appr`, title: '项目立项', icon: 'flag-o'},
    {path: `${pre}comp`, title: '数据库比对', icon: 'compress'}
  ])('/proj/')
}
