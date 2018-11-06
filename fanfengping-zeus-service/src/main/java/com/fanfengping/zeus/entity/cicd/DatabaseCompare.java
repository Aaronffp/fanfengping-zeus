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
public class DatabaseCompare implements Serializable {
    private int id;	               // 系统编号
    private String flag;	       // 批次标识
    private int status;	           // 比对结果（0，成功；-1，失败）
    private String eng;	           // 系统英文简称
    private int benchmarkId;       // 基准库编号
    private String benchmarkEnv;   // 基准库环境标识
    private String benchmarkUrl;   // 基准库URL
    private int targetId;	       // 比对库编号
    private String targetEnv;	   // 比对库环境标识
    private String targetUrl;	   // 比对库URL
    private String tableName;	   // 数据库表名
    private String info;	       // 比对结果信息
    private String benchmarkDetail;// 基准库详情
    private String targetDetail;   // 比对库详情
    private String note;	       // 备注
    private String ctime;	       // 创建日期

    public DatabaseCompare(String flag, int status, String eng,
                           int benchmarkId, String benchmarkEnv, String benchmarkUrl,
                           int targetId, String targetEnv, String targetUrl, String tableName,
                           String info, String benchmarkDetail, String targetDetail, String note) {
        this.flag = flag;
        this.status = status;
        this.eng = eng;
        this.benchmarkId = benchmarkId;
        this.benchmarkEnv = benchmarkEnv;
        this.benchmarkUrl = benchmarkUrl;
        this.targetId = targetId;
        this.targetEnv = targetEnv;
        this.targetUrl = targetUrl;
        this.tableName = tableName;
        this.info = info;
        this.benchmarkDetail = benchmarkDetail;
        this.targetDetail = targetDetail;
        this.note = note;
    }
}
