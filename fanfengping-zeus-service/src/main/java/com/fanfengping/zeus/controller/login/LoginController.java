package com.fanfengping.zeus.controller.login;

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

@Slf4j
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson login(@RequestBody String body) {
        ResponseJson responseJson = new ResponseJson(Codes.USER);

        try {
            JSONObject reqBody = JSON.parseObject(body);
            String username = reqBody.getString("username");
            String password = reqBody.getString("password");

            responseJson = responseJson.acti(Codes.USER_LOGIN).data("body", reqBody).oper(username);

            if (StringUtils.isEmpty(username) ||
                    username.length() < Codes.LENGTH_USER_ACCOUNT_MIN || username.length() > Codes.LENGTH_USER_ACCOUNT_MAX){
                responseJson = responseJson.fail(String.format("用户名为空或长度超出 %s-%s 个字符",
                                                                Codes.LENGTH_USER_ACCOUNT_MIN, Codes.LENGTH_USER_ACCOUNT_MAX));
                log.warn(responseJson.toString());
                return responseJson;
            }

            if (StringUtils.isEmpty(password) ||
                    password.length() < Codes.LENGTH_USER_PASSWORD_MIN || password.length() > Codes.LENGTH_USER_PASSWORD_MAX){
                responseJson = responseJson.fail(String.format("密码为空或长度超出 %d-%d 个字符",
                                                                Codes.LENGTH_USER_PASSWORD_MIN, Codes.LENGTH_USER_PASSWORD_MAX));
                log.warn(responseJson.toString());
                return responseJson;
            }

            User user = userService.findByUsernameAndPassword(username, password);

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
}
