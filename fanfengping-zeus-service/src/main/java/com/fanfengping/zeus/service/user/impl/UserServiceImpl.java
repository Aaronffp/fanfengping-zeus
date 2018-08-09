package com.fanfengping.zeus.service.user.impl;

import com.fanfengping.zeus.entity.user.ZeusUser;
import com.fanfengping.zeus.repository.user.UserRepository;
import com.fanfengping.zeus.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    /**
     * 通过账号、密钥查询用户信息
     * @param account
     * @param passwd
     * @return
     */
    @Override
    public ZeusUser findByAccountEqualsAndPasswdEquals(String account, String passwd) {
        return userRepository.findByAccountEqualsAndPasswdEquals(account, passwd);
    }
}
