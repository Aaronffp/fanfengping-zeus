package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ZeusGroupAndRole {
    @Id
    private String id;
    private String group_id;
    private String role_id;
    private String operator;
    private String ctime;
    private String utime;
}
