package com.fanfengping.zeus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanfengpingZeusServiceController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "{\"code\":200, \"status\":\"ok\", \"message\":\"Fanfengping Zeus Serivce ok.\"}";
    }
}
