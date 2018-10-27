package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.service.cicd.DatabaseCompareResultService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/db/compare")
public class DatabaseCompareResultController {
    @Autowired
    DatabaseCompareResultService databaseCompareResultService;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson findAllByConditions(@RequestParam("eng") String eng, @RequestParam("env") String env) {
        return databaseCompareResultService.findAllByConditions(eng, env);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson compare(@RequestBody Map<String,Object> reqMap) {
        String eng = reqMap.get("eng") == null ? "" : reqMap.get("eng").toString();
        String env = reqMap.get("env") == null ? "" : reqMap.get("env").toString();

        return databaseCompareResultService.compare(eng, env).data("requestParams", reqMap);
    }
}
