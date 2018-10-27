package com.fanfengping.zeus.controller.user;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.user.User;
import com.fanfengping.zeus.service.user.UserService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson add(@RequestBody User user) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_INSERT).data("requestParams", user);

        if (user == null) {
            responseJson.fail(999, "新增失败！原因：用户信息为空！");
            log.error(responseJson.toString());
            return responseJson;
        }

        return userService.add(user);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson update(@RequestBody User user) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_UPDATE).data("requestParams", user);

        if (user == null) {
            responseJson.fail(999, "更新失败！原因：用户信息为空！");
            log.error(responseJson.toString());
            return responseJson;
        }

        return userService.update(user);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson delete(@RequestBody User user) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_DELETE).data("requestParams", user);

        if (user == null) {
            responseJson.fail(999, "删除失败！原因：用户信息为空！");
            log.error(responseJson.toString());
            return responseJson;
        }

        return userService.delete(user);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseJson findAllByConditions(@RequestParam String username,
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
