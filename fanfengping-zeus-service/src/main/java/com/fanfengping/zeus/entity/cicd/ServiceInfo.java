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
public class ServiceInfo implements Serializable {
    private int id;                 // 系统主键
    private String env;            // 环境标识
    private String eng;            // 英文简称
    private String chs;            // 中文简称
    private String url;            // 访问链接
    private String account;        // 账号
    private String password;       // 密码
    private String note;           // 备注
    private String updater;        // 更新人
    private String utime;          // 更新时间

    public ServiceInfo(String env, String eng, String chs, String url, String account, String password,
                       String note, String updater) {
        this.env = env;
        this.eng = eng;
        this.chs = chs;
        this.url = url;
        this.account = account;
        this.password = password;
        this.updater = updater;
        this.note = note;
    }
}
