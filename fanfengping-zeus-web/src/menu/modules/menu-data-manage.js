export default {
  path: '',
  title: '数据管理',
  icon: 'database',
  children: (pre => [
    {path: `${pre}`, title: '数据库列表', icon: 'list'},
    {path: `${pre}dict`, title: '数据库字典', icon: 'book'},
    {path: `${pre}comp`, title: '数据库比对', icon: 'compress'}
  ])('/db/')
}
