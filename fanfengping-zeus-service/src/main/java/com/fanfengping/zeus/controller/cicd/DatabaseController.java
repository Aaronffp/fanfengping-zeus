package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.service.cicd.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/db")
public class DatabaseController {
    @Autowired
    DatabaseService databaseService;

    @RequestMapping(path = "", method = RequestMethod.POST , produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Database add(@RequestBody Database database) {
        return databaseService.add(database);
    }

    @RequestMapping(path = "", method = { RequestMethod.PUT }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Database update(@RequestBody Database database) {
        return databaseService.update(database);
    }

    @RequestMapping(path = "", method = { RequestMethod.DELETE }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Database delete(@RequestBody Database database) {
        return databaseService.delete(database);
    }

    @RequestMapping(path = "/benchmark", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Database setBenchmark(@RequestBody Database database) {
        return databaseService.setBenchmark(database);
    }

    @RequestMapping(path = "", method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Database> findAllByConditions(@RequestParam("env") String env, @RequestParam("eng") String eng, @RequestParam("chs") String chs) {
        List<Database> databases = databaseService.findAllByConditions(env, eng, chs);

        return databases;
    }
}

