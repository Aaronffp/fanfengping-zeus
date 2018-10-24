package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.service.cicd.DatabaseService;
import com.fanfengping.zeus.util.ResponseJson;
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
    public ResponseJson add(@RequestBody Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_INSERT);

        if (database == null) {
            responseJson.fail(999, "数据库信息不允许为空").data(null);
            log.warn(responseJson.toString());
            return responseJson;
        }

        Database db = databaseService.add(database);

        if (db == null) {
            responseJson.fail(999, "新增数据库失败").data(database);
            log.warn(responseJson.toString());
            return responseJson;
        }

        responseJson.succ(200, "成功新增").data(db);
        log.info(responseJson.toString());
        return responseJson;
    }

    @RequestMapping(path = "", method = { RequestMethod.PUT }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson update(@RequestBody Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_UPDATE);

        if (database == null) {
            responseJson.fail(999, "数据库信息不允许为空").data(null);
            log.warn(responseJson.toString());
            return responseJson;
        }

        Database db = databaseService.update(database);

        if (db == null) {
            responseJson.fail(999, "更新数据库失败").data(database);
            log.warn(responseJson.toString());
            return responseJson;
        }

        responseJson.succ(200, "成功更新").data(db);
        log.info(responseJson.toString());
        return responseJson;
    }

    @RequestMapping(path = "", method = { RequestMethod.DELETE }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson delete(@RequestBody Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_DELETE);

        if (database == null) {
            responseJson.fail(999, "数据库信息不允许为空").data(null);
            log.warn(responseJson.toString());
            return responseJson;
        }

        Database db = databaseService.delete(database);

        if (db == null) {
            responseJson.succ(200, "成功删除").data(database);
            log.warn(responseJson.toString());
            return responseJson;
        }

        responseJson.fail(999, "删除数据库失败").data(db);
        log.info(responseJson.toString());
        return responseJson;
    }

    @RequestMapping(path = "", method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson findAllByConditions(@RequestParam("env") String env, @RequestParam("valid") String valid, @RequestParam("eng") String eng, @RequestParam("chs") String chs) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_SEARCH);
        List<Database> databases = databaseService.findAllByConditions(env, valid, eng, chs);

        responseJson.succ(200, "成功查询").data(databases);
        return responseJson;
    }

    @RequestMapping(path = "/benchmark", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson setBenchmark(@RequestBody Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_BENCHMARK);

        if (database == null) {
            responseJson.fail(999, "基准库信息不允许为空").data(null);
            log.warn(responseJson.toString());
            return responseJson;
        }

        Database db = databaseService.setBenchmark(database);

        if (db == null) {
            responseJson.fail(999, "设置基准库失败").data(database);
            log.warn(responseJson.toString());
            return responseJson;
        }

        responseJson.succ(200, "成功设置基准库").data(db);
        log.info(responseJson.toString());
        return responseJson;
    }
}

