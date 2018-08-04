// 菜单 顶栏
export default [
  { path: '/index', title: '首页', icon: 'home' },
  {
      title: '数据库管理',
      icon: 'database',
      children: [
          { path: '/page1', title: '数据库列表' },
          { path: '/page2', title: '表结构比对' },
          { path: '/page3', title: '数据字典' }
    ]
  }
]
