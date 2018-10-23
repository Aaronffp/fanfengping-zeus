package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.DataDictionary;
import com.fanfengping.zeus.service.cicd.DataDictionaryService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ResponseJson responseJson = new ResponseJson(Codes.DICTIONARY, Codes.DICTIONARY_SEARCH);
        List<DataDictionary> dictionary = dataDictionaryService.findAllByConditions(env, eng, url, tableName, columnName);

        responseJson.succ(200, "成功查询").data(dictionary);
        log.info(responseJson.toString());
        return responseJson;
    }
}
