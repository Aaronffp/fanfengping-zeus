package com.fanfengping.zeus.entity.cicd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataDictionary {
    private int id;                  // 系统编号
    private String sn;               // 查询流水
    private int databaseId;          // 数据库ID
    private String env;              // 所属环境
    private String eng;              // 英文简称
    private String url;              // 数据库连接
    private String username;         // 账号
    private String password;         // 密码
    private String tableSchema;      // 数据库名称
    private String tableName;        // 表名称
    private String tableComment;     // 表注释
    private String engine;           // 表引擎
    private String tableCollation;   // 表字符集
    private String columnName;       // 字段名称
    private String columnComment;    // 字段注释
    private String columnKey;        // 是否主键
    private String columnType;       // 字段类型
    private String nullable;         // 是否可为空
    private String columnDefault;    // 默认值
    private String characterSetName; // 字段字符集
    private String collationName;    // 字段字符排序规则
    private String ctime;            // 创建日期（若半小时内存在生成数据，则不生成；否则，先生成后查询）

    public DataDictionary (String sn, int databaseId, String env, String eng, String url, String username, String password,
                           String tableSchema, String tableName, String tableComment,
                           String engine, String tableCollation,
                           String columnName, String columnComment, String columnKey,
                           String columnType, String nullable, String columnDefault,
                           String characterSetName, String collationName) {
        this.sn = sn;
        this.databaseId = databaseId;
        this.env = env;
        this.eng = eng;
        this.url = url;
        this.username = username;
        this.password = password;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.engine = engine;
        this.tableCollation = tableCollation;
        this.columnName = columnName;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
        this.columnType = columnType;
        this.nullable = nullable;
        this.columnDefault = columnDefault;
        this.characterSetName = characterSetName;
        this.collationName = collationName;
    }
}
