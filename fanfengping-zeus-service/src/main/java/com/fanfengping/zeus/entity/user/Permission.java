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
public class Permission implements Serializable {
    private int id;
    private int type;
    private String code;
    private String name;
    private String desc;
    private String operator;
    private String ctime;
    private String utime;

    public Permission(int type, String code, String name, String desc, String operator) {
        this.type = type;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.operator = operator;
    }
}
