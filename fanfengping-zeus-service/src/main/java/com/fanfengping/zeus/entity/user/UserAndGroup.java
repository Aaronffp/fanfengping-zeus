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
public class UserAndGroup implements Serializable {
    private int id;
    private String userId;
    private String groupId;
    private String operator;
    private String ctime;
    private String utime;

    public UserAndGroup(String userId, String groupId, String operator) {
        this.userId = userId;
        this.groupId = groupId;
        this.operator = operator;
    }
}
