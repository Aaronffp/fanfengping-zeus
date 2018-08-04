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