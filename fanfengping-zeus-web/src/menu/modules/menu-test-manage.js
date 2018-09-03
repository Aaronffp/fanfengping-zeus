export default {
  path: '',
  title: '测试管理',
  icon: 'balance-scale',
  children: (pre => [
    {path: `${pre}case`, title: '用例管理', icon: 'suitcase'},
    {path: `${pre}bug`, title: '缺陷管理', icon: 'bug'}
  ])('/test/')
}
