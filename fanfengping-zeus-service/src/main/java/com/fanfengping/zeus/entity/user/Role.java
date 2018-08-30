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
public class Role implements Serializable {
    private int    id;                    // 系统主键
    private String code;                  // 角色代码
    private String name;                  // 角色名称
    private String desc;                  // 角色描述
    private int    available;             // 是否可用（0、无效；1、有效）
    private String operator;              // 操作人
    private String ctime;                 // 创建时间
    private String utime;                 // 更新时间

    public Role(String code, String name, String desc, int available, String operator) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.available = available;
        this.operator = operator;
    }
}
