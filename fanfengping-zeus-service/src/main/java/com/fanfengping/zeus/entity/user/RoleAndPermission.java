package com.fanfengping.zeus.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleAndPermission implements Serializable {
    private int id;
    private int roleId;
    private int PermissionId;
    private String operator;
    private String ctime;
    private String utime;

    public RoleAndPermission(int roleId, int permissionId, String operator) {
        this.roleId = roleId;
        PermissionId = permissionId;
        this.operator = operator;
    }
}
