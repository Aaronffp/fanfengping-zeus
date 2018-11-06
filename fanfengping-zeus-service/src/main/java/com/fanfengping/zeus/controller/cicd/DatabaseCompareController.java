package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.service.cicd.DatabaseCompareService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/db/compare")
public class DatabaseCompareResultController {
    @Autowired
    DatabaseCompareService databaseCompareService;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson findAllByConditions(@RequestParam("eng") String eng,
                                            @RequestParam("bEnv") String bEnv,
                                            @RequestParam("tEnv") String tEnv,
                                            @RequestParam("tableName") String tableName) {
        return databaseCompareService.findAllByConditions(eng, bEnv, tEnv, tableName);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson compare(@RequestBody Map<String,Object> reqMap) {
        String eng = reqMap.get("eng") == null ? "" : reqMap.get("eng").toString();
        String bEnv = reqMap.get("bEnv") == null ? "" : reqMap.get("bEnv").toString();
        String tEnv = reqMap.get("tEnv") == null ? "" : reqMap.get("tEnv").toString();

        return databaseCompareService.compare(eng, bEnv, tEnv).data("requestParams", reqMap);
    }
}
