/**
 * 设置顶栏
 */
// 我的工作台
import menuWorkspace from './modules/menu-workspace'
// 畅所欲言
import menuCommunicate from './modules/menu-communicate'
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
import menuProjectManage from './modules/menu-project-manage'
// 发布管理
import menuPublishManage from './modules/menu-publish-manage'
// 开发管理
import menuDevelopManage from './modules/menu-dev-manage'
// 测试管理
import menuTestManage from './modules/menu-test-manage'
// 报表管理
import menuReportManage from './modules/menu-report-manage'
// 数据管理
import menuDataManage from './modules/menu-data-manage'
// 自动化
import menuAutoTest from './modules/menu-autotest'
// 知识库
import menuKnowledgeWiki from './modules/menu-knowledge-wiki'
// 运维管理
import menuCicdManage from './modules/menu-ops-manage'
// 系统管理
import menuSystemManage from './modules/menu-system-manage'
// 系统设置
import menuSystemSettings from './modules/menu-system-settings'

// 菜单 侧边栏
export const menuAside = [
  menuHomepage,
  menuProjectManage,
  menuPublishManage,
  menuDevelopManage,
  menuTestManage,
  menuReportManage,
  menuDataManage,
  menuAutoTest,
  menuKnowledgeWiki,
  menuCicdManage,
  menuSystemManage,
  menuSystemSettings
]

// 菜单 顶栏
export const menuHeader = [
  menuHomepage,
  menuWorkspace,
  menuCommunicate,
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
