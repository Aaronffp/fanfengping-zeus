package com.fanfengping.zeus.service.user.impl;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.user.User;
import com.fanfengping.zeus.repository.user.UserRepository;
import com.fanfengping.zeus.service.user.UserService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseJson add(User user) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_INSERT).data("requestParams", user);

        try {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                userRepository.add(user);
                responseJson.succ(200, "成功新增！").data(userRepository.findByUsername(user.getUsername()));
            } else {
                responseJson.fail(999, "新增失败！原因：用户已经存在！");
                log.error(responseJson.toString());
            }
        } catch (Exception e) {
            responseJson.fail(999, "新增失败！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson update(User user) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_UPDATE).data("requestParams", user);

        try {
            userRepository.update(user);
            responseJson.succ(200, "成功更新！").data(userRepository.findByUsername(user.getUsername()));
            log.info(responseJson.toString());
        } catch (Exception e) {
            responseJson.fail(999, "更新失败！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson delete(User user) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_DELETE).data("requestParams", user);

        try {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                responseJson.fail(200, "成功删除！");
                log.warn(responseJson.toString());
                return responseJson;
            }

            userRepository.delete(user);
            responseJson.succ(200, "成功删除！");
            log.info(responseJson.toString());
        } catch (Exception e) {
            responseJson.fail(999, "删除失败！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson findAllByConditions(String username, String name, String mobile, String email) {
        ResponseJson responseJson = new ResponseJson(Codes.USER, Codes.USER_SEARCH);
        responseJson.succ(200, "成功查询！").data(userRepository.findAllByConditions(username, name, mobile, email));
        log.info(responseJson.toString());
        return responseJson;
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
