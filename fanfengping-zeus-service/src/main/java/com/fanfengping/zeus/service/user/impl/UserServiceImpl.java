package com.fanfengping.zeus.service.user.impl;

import com.fanfengping.zeus.entity.user.User;
import com.fanfengping.zeus.repository.user.UserRepository;
import com.fanfengping.zeus.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User add(User user) {
        if (user != null && userRepository.add(user) > 0) {
            return userRepository.findByAccount(user.getAccount());
        } else {
            log.error("用户新增失败！原因：用户信息为空或系统未知异常！");
            return null;
        }
    }

    public User update(User user) {
        if (user != null && userRepository.update(user) > 0) {
            return userRepository.findByAccount(user.getAccount());
        } else {
            log.error("用户更新失败！原因：用户信息为空或系统未知异常！");
            return null;
        }
    }

    public User delete(User user) {
        if (user != null && userRepository.delete(user) > 0) {
            return user;
        } else {
            log.error("用户删除失败！原因：用户信息为空或系统未知异常！");
            return null;
        }
    }

    public List<User> findAllByConditions(String account, String name, String mobile, String email) {
        return userRepository.findAllByConditions(account, name, mobile, email);
    }

    public User findByAccountAndPasswd(String account, String passwd) {
        return userRepository.findByAccountAndPasswd(account, passwd);
    }
}
