package com.fanfengping.zeus.service.user;

import com.fanfengping.zeus.entity.user.User;
import com.fanfengping.zeus.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User> {
    List<User> findAllByConditions(String account, String name, String mobile, String email);

    User findByAccountAndPasswd (String account, String passwd);
}
