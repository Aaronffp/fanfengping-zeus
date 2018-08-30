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
public class DatabaseCompareResult implements Serializable {
    private int id;	               // 系统编号
    private String flag;	       // 批次标识
    private int status;	           // 结果标识（0，成功；-1，失败）
    private String eng;	           // 系统英文简称
    private int benchmarkId;       // 基准库编号
    private String benchmarkEnv;   // 基准库环境标识
    private String benchmarkUrl;   // 基准库URL
    private int targetId;	       // 比对库编号
    private String targetEnv;	   // 比对库环境标识
    private String targetUrl;	   // 比对库URL
    private String info;	       // 比对信息
    private String note;	       // 备注
    private String ctime;	       // 创建日期

    public DatabaseCompareResult(String flag, int status, String eng,
                                 int benchmarkId, String benchmarkEnv, String benchmarkUrl,
                                 int targetId, String targetEnv, String targetUrl, String info, String note) {
        this.flag = flag;
        this.status = status;
        this.eng = eng;
        this.benchmarkId = benchmarkId;
        this.benchmarkEnv = benchmarkEnv;
        this.benchmarkUrl = benchmarkUrl;
        this.targetId = targetId;
        this.targetEnv = targetEnv;
        this.targetUrl = targetUrl;
        this.info = info;
        this.note = note;
    }
}
