export default {
  path: '',
  title: '发布管理',
  icon: 'tags',
  children: (pre => [
    {path: `${pre}`, title: '发布管理', icon: 'tag'},
    {path: `${pre}chan`, title: '变更管理', icon: 'exchange'}
  ])('/pub/')
}
