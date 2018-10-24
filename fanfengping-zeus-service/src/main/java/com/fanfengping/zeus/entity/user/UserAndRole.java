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
public class UserAndRole implements Serializable {
    private Integer id;         // 系统主键
    private Integer userId;     // 系统主键
    private Integer roleId;     // 授权类型（1，菜单；2，按钮）
    private String operator;    // 操作人
    private String ctime;       // 创建时间
    private String utime;       // 更新时间

    public UserAndRole(Integer userId, Integer roleId, String operator) {
        this.userId = userId;
        this.roleId = roleId;
        this.operator = operator;
    }
}
