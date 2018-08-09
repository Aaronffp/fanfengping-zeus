package com.fanfengping.zeus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanfengpingZeusServiceController {
    private static final Logger log = LoggerFactory.getLogger(FanfengpingZeusServiceController.class);

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        log.info("{\"code\":200, \"status\":\"ok\", \"message\":\"Fanfengping Zeus Serivce ok.\"}");
        return "{\"code\":200, \"status\":\"ok\", \"message\":\"Fanfengping Zeus Serivce ok.\"}";
    }
}
