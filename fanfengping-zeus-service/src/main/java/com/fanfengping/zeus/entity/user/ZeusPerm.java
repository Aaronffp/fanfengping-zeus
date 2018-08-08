package com.fanfengping.zeus.entity.user;

import javax.persistence.*;
import java.util.List;

@Entity
public class ZeusPerm {
    @Id
    private int id;
    private String type;
    private String code;
    private String name;
    private String desc;
    private String operator;
    private String ctime;
    private String utime;

    // 权限 - 角色关系定义：多对多关系
    @OneToMany
    @JoinTable(name = "ZeusRoleAndPerm", joinColumns = {@JoinColumn(name = "perm_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<ZeusRole> roleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
