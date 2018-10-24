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
public class GroupAndRole implements Serializable {
    private int id;
    private int groupId;
    private int roleId;
    private String operator;
    private String ctime;
    private String utime;

    public GroupAndRole(int groupId, int roleId, String operator) {
        this.groupId = groupId;
        this.roleId = roleId;
        this.operator = operator;
    }
}
