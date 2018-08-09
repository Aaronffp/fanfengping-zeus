package com.fanfengping.zeus.service.user;

import com.fanfengping.zeus.entity.user.ZeusUser;

public interface UserService {
    /**
     * 通过账号、密钥查询账户信息
     * @param account
     * @param passwd
     * @return
     */
    ZeusUser findByAccountEqualsAndPasswdEquals(String account, String passwd);
}
