package com.fanfengping.zeus.controller;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.util.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanfengpingZeusServiceController {
    private static final Logger log = LoggerFactory.getLogger(FanfengpingZeusServiceController.class);
    private ResponseJson responseJson = new ResponseJson(Codes.SERVICE, Codes.SERVICE_STATUS);

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseJson index() {
        responseJson.succ(200,"Fanfengping Zeus Serivce ok.");
        log.info(responseJson.toString());
        return responseJson;
    }
}
