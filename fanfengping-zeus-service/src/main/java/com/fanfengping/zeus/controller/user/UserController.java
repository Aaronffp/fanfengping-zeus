package com.fanfengping.zeus.controller.user;

import com.fanfengping.zeus.entity.user.ZeusUser;
import com.fanfengping.zeus.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 通过账号、密钥查询账户信息
     * @param account
     * @param passwd
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{account}/{passwd}")
    public ZeusUser getUserInfo(@PathVariable String account, @PathVariable String passwd){
        return userService.findByAccountAndPasswd(account, passwd);
    }
}
