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
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_INSERT).data("requestParams", database);

        if (database == null) {
            responseJson.fail(999, "数据库信息不允许为空");
            log.warn(responseJson.toString());
            return responseJson;
        }

        return databaseService.add(database);
    }

    @RequestMapping(path = "", method = { RequestMethod.PUT }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson update(@RequestBody Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_UPDATE).data("requestParams", database);

        if (database == null) {
            responseJson.fail(999, "更新失败！原因：数据库信息不允许为空");
            log.error(responseJson.toString());
            return responseJson;
        }

        return databaseService.update(database);
    }

    @RequestMapping(path = "", method = { RequestMethod.DELETE }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson delete(@RequestBody Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_DELETE).data("requestParams", database);

        if (database == null) {
            responseJson.succ(200, "成功删除！");
            log.warn(responseJson.toString());
            return responseJson;
        }

        return databaseService.delete(database);
    }

    @RequestMapping(path = "", method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson findAllByConditions(@RequestParam("env") String env, @RequestParam("valid") String valid, @RequestParam("eng") String eng, @RequestParam("chs") String chs) {
        return databaseService.findAllByConditions(env, valid, eng, chs);
    }
}

