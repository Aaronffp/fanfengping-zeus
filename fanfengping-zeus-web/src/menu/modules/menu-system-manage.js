export default {
  path: '',
  title: '系统管理',
  icon: 'cog',
  children: (pre => [
    { path: `${pre}user`, title: '用户管理', icon: 'users' },
    { path: `${pre}group`, title: '组管理', icon: 'group' },
    { path: `${pre}role`, title: '角色管理', icon: 'id-card' },
    { path: `${pre}permission`, title: '权限管理', icon: 'gavel' },
    { path: `${pre}team`, title: '团队管理', icon: 'handshake-o' },
    { path: `${pre}prod`, title: '产品线管理', icon: 'product-hunt' }
  ])('/sys/')
}
