export default {
  path: '',
  title: '知识库',
  icon: 'wikipedia-w',
  children: (pre => [
    {path: `${pre}`, title: '知识库', icon: 'wikipedia-w'}
  ])('/wiki/')
}
