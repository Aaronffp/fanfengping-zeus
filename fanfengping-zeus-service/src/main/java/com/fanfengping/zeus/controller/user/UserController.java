package com.fanfengping.zeus.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.user.User;
import com.fanfengping.zeus.service.user.UserService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User delete(@RequestBody User user) {
        return userService.delete(user);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> findAllByConditions(@RequestParam String username,
                                          @RequestParam String name,
                                          @RequestParam String mobile,
                                          @RequestParam String email) {
        return userService.findAllByConditions(username, name, mobile, email);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{account}/{passwd}")
    public User getUserInfo(@PathVariable String username, @PathVariable String passwd){
        return userService.findByUsernameAndPassword(username, passwd);
    }
}
