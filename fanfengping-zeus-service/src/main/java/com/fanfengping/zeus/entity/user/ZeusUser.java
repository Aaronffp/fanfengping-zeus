package com.fanfengping.zeus.entity.user;

import javax.persistence.*;
import java.util.List;

/**
 * 说明：
 * 1、Entity的类名称应与数据库中对应表名称相一致，否则会报错提示找不到对应的表
 * 2、Repository、Controller的类名称与表名称无关
 * 3、单表中必须存在自增主键列（id），否则无法注册成功，提示如下所示信息：
 *    org.hibernate.AnnotationException: No identifier specified for entity: com.fanfengping.zeus.entity.user.ZeusUser
 */
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

    // 用户 - 角色关系定义：多对多关系
    @OneToMany
    @JoinTable(name = "ZeusUserAndRole", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<ZeusRole> roleList;           // 一个用户具有多个角色

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public List<ZeusRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ZeusRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 重新定义秘钥
     * @return
     */
    public String getCredentialsSalt() {
        return this.account + this.passwd;
    }
}
