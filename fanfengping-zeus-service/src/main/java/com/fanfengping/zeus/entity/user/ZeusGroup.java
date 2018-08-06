package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ZeusGroup {
    @Id
    private int id;
    private String code;
    private String name;
    private String desc;
    private String operator;
    private String ctime;
    private String utime;
}
