package com.fanfengping.zeus.entity.user;

import javax.persistence.*;
import java.util.List;

@Entity
public class ZeusRole {
    @Id
    private int id;
    private String code;
    private String name;
    private String desc;
    private String operator;
    private String ctime;
    private String utime;

    // 角色 - 用户关系定义：多对多关系
    @OneToMany
    @JoinTable(name = "ZeusUserAndRole", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<ZeusUser> userList;

    // 角色 - 授权关系定义：多对多关系
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ZeusRoleAndPerm", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    private List<ZeusPerm> permList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public List<ZeusUser> getUserList() {
        return userList;
    }

    public void setUserList(List<ZeusUser> userList) {
        this.userList = userList;
    }

    public List<ZeusPerm> getPermList() {
        return permList;
    }

    public void setPermList(List<ZeusPerm> permList) {
        this.permList = permList;
    }
}
