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
public class BuildHistory implements Serializable {
    private int id;               // 系统主键
    private String eng;           // 英文简称
    private String tag;           // 构建版本
    private String status;        // 版本状态（1，发布；0，测试版本；-1，开发版本）
    private String gitUrl;        // GIT仓库地址
    private String gitBranch;     // GIT分支
    private String addr;          // 构建服务器
    private String link;          // 构建链接
    private String operator;      // 操作人
    private String ctime;         // 构建时间

    public BuildHistory(String eng, String tag, String status, String gitUrl, String gitBranch,
                        String addr, String link, String operator) {
        this.eng = eng;
        this.tag = tag;
        this.status = status;
        this.gitUrl = gitUrl;
        this.gitBranch = gitBranch;
        this.addr = addr;
        this.link = link;
        this.operator = operator;
    }
}
