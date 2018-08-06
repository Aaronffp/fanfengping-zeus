package com.fanfengping.zeus.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ZeusRoleAndPerm {
    @Id
    private int id;
    private String role_id;
    private String perm_id;
    private String operator;
    private String ctime;
    private String utime;
}
