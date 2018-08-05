package com.fanfengping.zeus.controller;

import com.fanfengping.zeus.entity.ZeusUser;
import com.fanfengping.zeus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/{account}/{passwd}")
    public ZeusUser getUserInfo(@PathVariable String account, @PathVariable String passwd){
        return userRepository.findOneByAccountAndPasswd(account, passwd);
    }
}
