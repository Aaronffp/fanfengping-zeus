export default {
    path: '',
    title: '工具库',
    icon: 'medkit',
    children: (pre => [
        {path: `${pre}md5`, title: 'MD5加密', icon: 'barcode'},
        {path: `${pre}aes`, title: 'AES加密', icon: 'qrcode'},
        {path: `${pre}gene`, title: '数据生成', icon: 'exchange'}
    ])('/tool/')
}
