package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.service.cicd.DatabaseCompareResultService;
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
    public List<DatabaseCompareResult> findAllByConditions(@RequestParam("service") String service, @RequestParam("env") String env)
            throws SQLException {
        return databaseCompareResultService.findAllByConditions(service, env);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String compare(@RequestBody Map<String,Object> reqMap) throws SQLException {
        String eng = reqMap.get("eng") == null ? "" : reqMap.get("eng").toString();
        String env = reqMap.get("env") == null ? "" : reqMap.get("env").toString();

        return databaseCompareResultService.compare(eng, env);
    }
}
