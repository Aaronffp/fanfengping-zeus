package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 说明：
 * 1、Entity的类名称应与数据库中对应表名称相一致，否则会报错提示找不到对应的表
 * 2、Repository、Controller的类名称与表名称无关
 * 3、单表中必须存在自增主键列（id），否则无法注册成功，提示如下所示信息：
 *    org.hibernate.AnnotationException: No identifier specified for entity: com.fanfengping.zeus.entity.user.ZeusUser
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
