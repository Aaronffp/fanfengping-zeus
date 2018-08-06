package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 说明：
 * 1、Entity的类名称应与数据库中对应表名称相一致，否则会报错提示找不到对应的表
 * 2、Repository、Controller的类名称与表名称无关
 */
@Data
@Entity
public class ZeusUser {
    @Id
    private String id;
    private String uuid;
    private String name;
    private String mobile;
    private String email;
    private String account;
    private String passwd;
    private String valid;
    private String operator;
    private String ctime;
    private String utime;
}
