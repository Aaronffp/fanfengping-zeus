-- 查询：系统编码类型信息
SELECT *
  FROM zeus_code_type zct
 WHERE 1 = 1 AND zct.valid = 1;

-- 查询：系统编码类型信息
SELECT *
  FROM zeus_code_detail zcd
 WHERE 1 = 1 AND zcd.valid = 1
 ORDER BY zcd.type_id, zcd.id;

-- 查询：系统编码详情信息
SELECT *
FROM zeus_code_type zct
LEFT JOIN zeus_code_detail zcd ON zct.id = zcd.type_id AND zcd.valid = 1
WHERE 1 = 1 AND zct.valid = 1
ORDER BY zcd.type_id, zcd.id;

-- 查询：首页快捷网站信息
SELECT *
  FROM zeus_homepage_shortcut zhs
 WHERE 1 = 1 AND zhs.dis = 1
 ORDER BY zhs.id;

-- 查询：用户列表
SELECT *
  FROM zeus_user zu
 WHERE 1 = 1 AND zu.valid = 1
 ORDER BY zu.id;

-- 查询：菜单列表
SELECT *
  FROM zeus_menu fir
  LEFT JOIN zeus_menu sec ON fir.id = SEC.menu_id AND sec.dis = 1
 WHERE fir.dis = 1 AND fir.level = 1
 ORDER BY fir.disorder, sec.disorder;


