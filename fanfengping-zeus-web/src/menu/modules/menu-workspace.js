export default {
  path: '',
  title: '我的工作台',
  icon: 'desktop',
  children: (pre => [
    {path: `${pre}info`, title: '个人信息', icon: 'id-badge'},
    { path: `${pre}plan`, title: '工作计划', icon: 'paper-plane' },
    { path: `${pre}report`, title: '周报管理', icon: 'group' },
    { path: `${pre}sign`, title: '日常打卡', icon: 'bookmark' },
    { path: `${pre}kaoqin`, title: '考勤信息', icon: 'bell' }
  ])('/work/')
}
