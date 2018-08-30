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
public class User implements Serializable {
    private int    id;                    // 系统主键
    private String uuid;                  // UUID
    private String name;                  // 用户名称
    private String mobile;                // 手机号码
    private String email;                 // 用户邮箱
    private String account;               // 用户账号（字母数字下划线，字母开头）
    private String passwd;                // 用户密码
    private String salt;                  // 秘钥
    private int    state;                 // 用户状态（默认0。0、创建未认证；1、正常、2、锁定）
    private String operator;              // 操作人
    private String ctime;                 // 创建时间
    private String utime;                 // 更新时间

    public User(String uuid, String name, String mobile, String email,
                String account, String passwd, String salt, int state, String operator) {
        this.uuid = uuid;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.account = account;
        this.passwd = passwd;
        this.salt = salt;
        this.state = state;
        this.operator = operator;
    }

    /**
     * 重新定义秘钥
     * @return
     */
    public String getCredentialsSalt() {
        return this.passwd + this.salt;
    }
}
