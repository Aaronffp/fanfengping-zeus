export default {
  path: '',
  title: '开发管理',
  icon: 'code',
  children: (pre => [
    {path: `${pre}vers`, title: '版本管理', icon: 'gitlab'},
    {path: `${pre}desi`, title: '系统设计', icon: 'coffee'},
    {path: `${pre}apiw`, title: '接口文档', icon: 'sliders'},
    {path: `${pre}stor`, title: '用户故事', icon: 'user-circle'},
    {path: `${pre}tasks`, title: '开发任务', icon: 'tasks'}
  ])('/dev/')
}
