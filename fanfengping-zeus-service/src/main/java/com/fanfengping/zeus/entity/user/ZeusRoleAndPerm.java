package com.fanfengping.zeus.entity.user;

import javax.persistence.*;
import java.util.List;

@Entity
public class ZeusRoleAndPerm {
    @Id
    private int id;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "perm_id")
    private String permId;
    private String operator;
    private String ctime;
    private String utime;

    @ManyToMany
    @JoinTable(name = "ZeusRole", joinColumns = {@JoinColumn(name = "id", referencedColumnName = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    public List<ZeusRole> userList;

    @ManyToMany
    @JoinTable(name = "ZeusPerm", joinColumns = {@JoinColumn(name = "id", referencedColumnName = "perm_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<ZeusPerm> permList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
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

    public List<ZeusRole> getUserList() {
        return userList;
    }

    public void setUserList(List<ZeusRole> userList) {
        this.userList = userList;
    }

    public List<ZeusPerm> getPermList() {
        return permList;
    }

    public void setPermList(List<ZeusPerm> permList) {
        this.permList = permList;
    }
}
