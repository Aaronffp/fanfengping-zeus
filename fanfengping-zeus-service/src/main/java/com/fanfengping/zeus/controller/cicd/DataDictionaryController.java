package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.service.cicd.DataDictionaryService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/db/dict")
public class DataDictionaryController {
    @Autowired
    DataDictionaryService dataDictionaryService;

    @RequestMapping(path = "", method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson findAllByConditions(@RequestParam("env") String env,
                                            @RequestParam("eng") String eng,
                                            @RequestParam("url") String url,
                                            @RequestParam("tableName") String tableName,
                                            @RequestParam("columnName") String columnName) {
        return dataDictionaryService.findAllByConditions(env, eng, url, tableName, columnName);
    }
}
