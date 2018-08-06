package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
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
}
