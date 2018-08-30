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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User add(@RequestBody Map<String, Object> reqMap) {
        String uuid = reqMap.get("uuid").toString();
        String name = reqMap.get("name").toString();
        String mobile = reqMap.get("mobile").toString();
        String email = reqMap.get("email").toString();
        String account = reqMap.get("account").toString();
        String passwd = reqMap.get("passwd").toString();
        String salt = reqMap.get("salt").toString();
        int state = Integer.parseInt(reqMap.get("state").toString());
        String operator = reqMap.get("operator").toString();

        User user = new User(uuid, name, mobile, email, account, passwd, salt, state, operator);

        return userService.add(user);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User update(@RequestBody Map<String, Object> reqMap) {
        int id = Integer.parseInt(reqMap.get("id").toString());
        String uuid = reqMap.get("uuid").toString();
        String name = reqMap.get("name").toString();
        String mobile = reqMap.get("mobile").toString();
        String email = reqMap.get("email").toString();
        String account = reqMap.get("account").toString();
        String passwd = reqMap.get("passwd").toString();
        String salt = reqMap.get("salt").toString();
        int state = Integer.parseInt(reqMap.get("state").toString());
        String operator = reqMap.get("operator").toString();
        String ctime = reqMap.get("ctime").toString();
        String utime = reqMap.get("utime").toString();

        User user = new User(id, uuid, name, mobile, email, account, passwd, salt, state, operator, ctime, utime);

        return userService.update(user);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public User delete(@RequestBody Map<String, Object> reqMap) {
        Integer id = Integer.parseInt(reqMap.get("id").toString());
        String uuid = reqMap.get("uuid").toString();
        String name = reqMap.get("name").toString();
        String mobile = reqMap.get("mobile").toString();
        String email = reqMap.get("email").toString();
        String account = reqMap.get("account").toString();
        String passwd = reqMap.get("passwd").toString();
        String salt = reqMap.get("salt").toString();
        int state = Integer.parseInt(reqMap.get("state").toString());
        String operator = reqMap.get("operator").toString();
        String ctime = reqMap.get("ctime").toString();
        String utime = reqMap.get("utime").toString();

        User user = new User(id, uuid, name, mobile, email, account, passwd, salt, state, operator, ctime, utime);

        return userService.delete(user);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> findAllByConditions(@RequestParam String account,
                                          @RequestParam String name,
                                          @RequestParam String mobile,
                                          @RequestParam String email) {
        return userService.findAllByConditions(account, name, mobile, email);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @ResponseBody
    public ResponseJson login(@RequestBody String body) {
        ResponseJson responseJson = new ResponseJson(Codes.USER);

        try {
            JSONObject reqBody = JSON.parseObject(body);
            String account = reqBody.getString("account");
            String passwd = reqBody.getString("passwd");

            responseJson = responseJson.acti(Codes.USER_LOGIN).data("body", reqBody).oper(account);

            if (StringUtils.isEmpty(account) ||
                    account.length() < Codes.LENGTH_USER_ACCOUNT_MIN ||
                    account.length() > Codes.LENGTH_USER_ACCOUNT_MAX){
                responseJson = responseJson.fail(String.format("用户名为空或长度超出 %s-%s 个字符", Codes.LENGTH_USER_ACCOUNT_MIN, Codes.LENGTH_USER_ACCOUNT_MAX));
                log.warn(responseJson.toString());
                return responseJson;
            }

            if (StringUtils.isEmpty(passwd) ||
                    passwd.length() < Codes.LENGTH_USER_PASSWORD_MIN ||
                    passwd.length() > Codes.LENGTH_USER_PASSWORD_MAX){
                responseJson = responseJson.fail(String.format("密码为空或长度超出 %d-%d 个字符", Codes.LENGTH_USER_PASSWORD_MIN, Codes.LENGTH_USER_PASSWORD_MAX));
                log.warn(responseJson.toString());
                return responseJson;
            }

            User user = userService.findByAccountAndPasswd(account, passwd);

            if (user == null){
                responseJson = responseJson.fail("用户不存在或密码错误");
                log.warn(responseJson.toString());
                return responseJson;
            } else {
                responseJson = responseJson.succ(user);
                log.info(responseJson.toString());
                return responseJson;
            }
        } catch ( Exception e ) {
            responseJson = responseJson.fail(e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{account}/{passwd}")
    public User getUserInfo(@PathVariable String account, @PathVariable String passwd){
        return userService.findByAccountAndPasswd(account, passwd);
    }
}
