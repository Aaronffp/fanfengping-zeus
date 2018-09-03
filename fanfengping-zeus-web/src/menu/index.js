// 插件
import demoPlugins from './modules/demo-plugins'
// 组件
import demoComponents from './modules/demo-components'
// 组件
import demoCharts from './modules/demo-charts'
// 组件库
import demoElement from './modules/demo-element'
// 试验台
import demoPlayground from './modules/demo-playground'
// 示例
import demoBusiness from './modules/demo-business'
// CRUD
import demoD2Crud from './modules/demo-d2-crud'
// 第三方网页
import demoFrame from './modules/demo-frame'

/**
 * 设置侧边栏
 */
// 首页
import menuHomepage from './modules/menu-homepage'
// 项目管理
// 发布管理
// 开发管理
// 测试管理
// 报表管理
// 数据管理
// 自动化
// 知识库
// 运维管理
// 系统管理
// 系统设置


// 菜单 侧边栏
export const menuAside = [
    menuHomepage
]

// 菜单 顶栏
export const menuHeader = [
  {
    path: '/index',
    title: '首页',
    icon: 'home'
  },
  {
    title: '功能',
    icon: 'puzzle-piece',
    children: [
      demoD2Crud,
      demoComponents,
      demoElement,
      demoCharts,
      demoPlugins,
      demoFrame,
      {
        title: '新窗口打开链接',
        icon: 'link',
        children: [
          { path: 'https://github.com/d2-projects/d2-admin', title: 'D2Admin Github', icon: 'github' },
          { path: 'https://juejin.im/user/57a48b632e958a006691b946/posts', title: '掘金', icon: 'globe' },
          { path: 'https://my.oschina.net/u/3871516', title: '开源中国', icon: 'globe' },
          { path: 'https://www.zhihu.com/people/fairy-ever/activities', title: '知乎', icon: 'globe' },
          { path: 'https://segmentfault.com/blog/liyang-note-book', title: 'segmentfault 专栏', icon: 'globe' },
          { path: 'http://www.fairyever.com/', title: 'www.fairyever.com', icon: 'globe' }
        ]
      }
    ]
  },
  demoPlayground,
  demoBusiness
]
