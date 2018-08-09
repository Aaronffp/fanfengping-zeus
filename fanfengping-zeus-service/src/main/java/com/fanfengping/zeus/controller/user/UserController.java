package com.fanfengping.zeus.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.user.ZeusUser;
import com.fanfengping.zeus.service.user.UserService;
import com.fanfengping.zeus.util.Json;
import org.apache.commons.lang3.StringUtils;
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
     * 通过账号、密钥查询用户信息
     * @param body
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @ResponseBody
    public Json login(@RequestBody String body) {
        Json json = new Json(Codes.USER);
        try {
            JSONObject reqBody = JSON.parseObject(body);
            String account = reqBody.getString("account");
            String passwd = reqBody.getString("passwd");

            json = json.acti(Codes.USER_LOGIN).data("body", reqBody).oper(account);

            if (StringUtils.isEmpty(account) || account.length() < Codes.LENGTH_USER_ACCOUNT_MIN || account.length() > Codes.LENGTH_USER_ACCOUNT_MAX){
                json = json.fail(String.format("用户名为空或长度超出 %s-%s 个字符", Codes.LENGTH_USER_ACCOUNT_MIN, Codes.LENGTH_USER_ACCOUNT_MAX));
                log.warn(json.toString());
                return json;
            }

            if (StringUtils.isEmpty(passwd) || passwd.length() < Codes.LENGTH_USER_PASSWORD_MIN || passwd.length() > Codes.LENGTH_USER_PASSWORD_MAX){
                json = json.fail(String.format("密码为空或长度超出 %d-%d 个字符", Codes.LENGTH_USER_PASSWORD_MIN, Codes.LENGTH_USER_PASSWORD_MAX));
                log.warn(json.toString());
                return json;
            }

            ZeusUser user = userService.findByAccountEqualsAndPasswdEquals(account, passwd);

            if (user == null){
                json = json.fail("用户不存在或密码错误");
                log.warn(json.toString());
                return json;
            } else {
                json = json.succ(user);
                log.info(json.toString());
                return json;
            }
        } catch ( Exception e ) {
            json = json.fail(e.getMessage());
            log.error(json.toString(), e);
            return json;
        }
    }

    /**
     * 通过账号、密钥查询账户信息
     * @param account
     * @param passwd
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{account}/{passwd}")
    public ZeusUser getUserInfo(@PathVariable String account, @PathVariable String passwd){
        return userService.findByAccountEqualsAndPasswdEquals(account, passwd);
    }
}
