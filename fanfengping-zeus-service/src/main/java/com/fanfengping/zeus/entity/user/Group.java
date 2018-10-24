package com.fanfengping.zeus.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private int id;
    private String code;
    private String name;
    private String desc;
    private String operator;
    private String ctime;
    private String utime;

    public Group(String code, String name, String desc, String operator) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.operator = operator;
    }
}
