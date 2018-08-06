package com.fanfengping.zeus.service.user;

import com.fanfengping.zeus.entity.user.ZeusUser;

public interface UserService {
    ZeusUser findOneByAccountAndPasswd(String account, String passwd);
}
