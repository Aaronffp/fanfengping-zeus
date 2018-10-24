export default {
  path: '',
  title: '畅所欲言',
  icon: 'comments',
  children: (pre => [
    {path: `${pre}suggest`, title: '规划建议', icon: 'envelope'},
    {path: `${pre}bug`, title: '提交问题', icon: 'bug'}
  ])('/enve/')
}
