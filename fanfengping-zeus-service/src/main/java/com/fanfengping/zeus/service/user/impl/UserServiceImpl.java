package com.fanfengping.zeus.service.user.impl;

import com.fanfengping.zeus.entity.user.ZeusUser;
import com.fanfengping.zeus.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserService userService;

    @Override
    public ZeusUser findOneByAccountAndPasswd(String account, String passwd) {
        return userService.findOneByAccountAndPasswd(account, passwd);
    }
}
