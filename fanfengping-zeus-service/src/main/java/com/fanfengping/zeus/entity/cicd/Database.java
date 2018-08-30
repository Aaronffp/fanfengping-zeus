package com.fanfengping.zeus.entity.cicd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Database implements Serializable {
    private int id;           // 系统主键
    private String env;       // 环境标识
    private String eng;       // 英文简称
    private String chs;       // 中文简称
    private int benchmark;    // 基准数据库（1，是；0，否）
    private String type;      // 数据库类型
    private String driver;    // 数据库驱动
    private String url;       // 数据库URL
    private String account;   // 数据库账号
    private String password;  // 数据库密码
    private String creater;   // 维护人
    private String ctime;     // 创建日期
    private String updater;   // 更新人
    private String utime;     // 更新时间
    private String note;      // 备注

    public Database(String env, String eng, String chs, Integer benchmark, String type,
                    String driver, String url, String account, String password,
                    String creater, String updater, String note) {
        this.env = env;
        this.eng = eng;
        this.chs = chs;
        this.benchmark = benchmark;
        this.type = type;
        this.driver = driver;
        this.url = url;
        this.account = account;
        this.password = password;
        this.creater = creater;
        this.updater = updater;
        this.note = note;
    }
}
