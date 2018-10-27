package com.fanfengping.zeus.service.user;

import com.fanfengping.zeus.entity.user.User;
import com.fanfengping.zeus.service.BaseService;
import com.fanfengping.zeus.util.ResponseJson;

import java.util.List;

public interface UserService extends BaseService<User> {
    ResponseJson findAllByConditions(String username, String name, String mobile, String email);

    User findByUsernameAndPassword (String username, String password);
}
