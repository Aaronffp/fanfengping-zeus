package com.fanfengping.zeus.entity.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeType implements Serializable {
    private int id;
    private int available;
    private int code;
    private String name;
    private String operator;
    private String note;
    private String ctime;
    private String utime;

    public CodeType(int available, int code, String name, String operator, String note) {
        this.available = available;
        this.code = code;
        this.name = name;
        this.operator = operator;
        this.note = note;
    }
}
