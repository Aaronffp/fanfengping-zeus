export default {
  path: '',
  title: '系统设置',
  icon: 'cogs',
  children: (pre => [
    {path: `${pre}menu`, title: '菜单设置', icon: 'th-list'},
    {path: `${pre}code`, title: '编码设置', icon: 'barcode'},
    {path: `${pre}task`, title: '定时任务', icon: 'tasks'}
  ])('/sys/')
}
