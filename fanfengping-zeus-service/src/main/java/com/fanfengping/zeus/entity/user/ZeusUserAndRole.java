package com.fanfengping.zeus.entity.user;

import javax.persistence.*;
import java.util.List;

@Entity
public class ZeusUserAndRole {
    @Id
    private int id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "role_id")
    private String roleId;
    private String operator;
    private String ctime;
    private String utime;

    @ManyToMany
    @JoinTable(name = "ZeusUser", joinColumns = {@JoinColumn(name = "id", referencedColumnName = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<ZeusUser> userList;

    @ManyToMany
    @JoinTable(name = "ZeusRole", joinColumns = {@JoinColumn(name = "id", referencedColumnName = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public List<ZeusRole> roleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public List<ZeusRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ZeusRole> roleList) {
        this.roleList = roleList;
    }
}
