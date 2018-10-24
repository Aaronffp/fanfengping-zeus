export default {
  path: '',
  title: '报表管理',
  icon: 'dashboard',
  children: (pre => [
    {path: `${pre}`, title: '仪表盘', icon: 'dashboard'},
    {path: `${pre}pub`, title: '发布报表', icon: 'line-chart'},
    {path: `${pre}proj`, title: '项目报表', icon: 'area-chart'},
    {path: `${pre}corp`, title: '团队报表', icon: 'bar-chart'},
    {path: `${pre}case`, title: '用例报表', icon: 'pie-chart'},
    {path: `${pre}bug`, title: '缺陷报表', icon: 'bar-chart-o'}
  ])('/repo/')
}
