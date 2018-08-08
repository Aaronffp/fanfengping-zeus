package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ZeusGroup {
    @Id
    private int id;
    private String code;
    private String name;
    private String desc;
    private String operator;
    private String ctime;
    private String utime;

    // 角色 - 组关系定义：多对多关系
    @OneToMany
    @JoinTable(name = "ZeusGroupAndRole", joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<ZeusRole> roleList;

    // 角色 - 用户关系定义：多对多关系
    @OneToMany
    @JoinTable(name = "ZeusUserAndGroup", joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<ZeusUser> userList;

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

    public List<ZeusRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ZeusRole> roleList) {
        this.roleList = roleList;
    }

    public List<ZeusUser> getUserList() {
        return userList;
    }

    public void setUserList(List<ZeusUser> userList) {
        this.userList = userList;
    }
}
