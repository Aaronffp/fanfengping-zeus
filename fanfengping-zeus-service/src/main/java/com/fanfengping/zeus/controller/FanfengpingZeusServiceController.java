package com.fanfengping.zeus.controller;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanfengpingZeusServiceController {
    private static final Logger log = LoggerFactory.getLogger(FanfengpingZeusServiceController.class);
    private Json json = new Json(Codes.SERVICE, Codes.SERVICE_STATUS);

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Json index() {
        json.succ("system","Fanfengping Zeus Serivce ok.");
        log.info(json.toString());
        return json;
    }
}
