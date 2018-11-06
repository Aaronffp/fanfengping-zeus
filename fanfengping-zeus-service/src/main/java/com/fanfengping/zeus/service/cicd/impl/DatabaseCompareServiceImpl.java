package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.entity.cicd.DatabaseCompare;
import com.fanfengping.zeus.repository.cicd.DatabaseCompareRepository;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DatabaseCompareService;
import com.fanfengping.zeus.util.MysqlUtil;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class DatabaseCompareServiceImpl implements DatabaseCompareService {
    @Autowired
    DatabaseCompareRepository databaseCompareRepository;

    @Autowired
    DatabaseRepository databaseRepository;

    @Autowired
    MysqlUtil mysqlUtil;

    public Integer add(DatabaseCompare databaseCompare) {
        return databaseCompareRepository.add(databaseCompare);
    }

    public ResponseJson findAllByConditions(String eng, String bEnv, String tEnv, String tableName) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_SEARCH);
        responseJson.succ(200, "成功查询").data(databaseCompareRepository.findAllByConditions(eng, bEnv, tEnv, tableName));
        log.info(responseJson.toString());
        return responseJson;
    }

    public ResponseJson compare(String eng, String bEnv, String tEnv) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_COMPARE);

        try {
            if (String.valueOf(bEnv).equals(tEnv)) {
                responseJson.succ(200, "基准库和比对库相同，无需比对！");
                log.info(responseJson.toString());
                return responseJson;
            }

            if (databaseCompareRepository.exist(eng, bEnv, tEnv).size() > 0) {
                responseJson.succ(200, "数据库表结构信息已存在十分钟内比对结果，敬请查询；或十分钟后重新比对！");
                log.info(responseJson.toString());
                return responseJson;
            }

            databaseCompareRepository.delete(eng, bEnv, tEnv);

            List<Database> dbs = databaseRepository.findAllBenchmark(eng, bEnv);
            responseJson.succ(200, "数据库比对基本信息").data("benchmarkDatabaseList", dbs);
            log.info(responseJson.toString());

            long start = System.currentTimeMillis();

            if (dbs.size() < 1) {
                responseJson.succ(200, "未存在基准库，请返回数据库列表页面确认！");
                log.info(responseJson.toString());
                return responseJson;
            }

            for (Database ds : dbs) {
                List<Database> dbt = databaseRepository.findAllComp(ds.getEng(), tEnv);
                responseJson.data("targetDatabaseList", dbt);
                log.info(responseJson.toString());

                if (dbt.size() < 1) {
                    continue;
                }

                for (Database dt : dbt) {
                    String timestamp = String.valueOf(System.currentTimeMillis());

                    DatabaseCompare databaseCompare = new DatabaseCompare(timestamp, 0, ds.getEng(), ds.getId(), ds.getEnv(), ds.getUrl(), dt.getId(), dt.getEnv(), dt.getUrl(),
                            "", "开始比对数据库...", "", "", "");

                    databaseCompareRepository.add(databaseCompare);

                    log.info(String.format("[%s：%s - %s：%s] - 开始比对数据库表信息...", ds.getEnv(), ds.getUrl(), dt.getEnv(), dt.getUrl()));
                    mysqlUtil.compareAllTables(ds, dt, timestamp);
                    log.info(String.format("[%s：%s - %s：%s] - 比对数据库表信息完成...", ds.getEnv(), ds.getUrl(), dt.getEnv(), dt.getUrl()));
                }
            }

            long end = System.currentTimeMillis();

            responseJson.succ(200, String.format("数据库比对完成，总计耗时：%d 分 %d 秒", (end - start)/1000/60, (end - start)/1000 % 60));
            responseJson.data(databaseCompareRepository.findAllByConditions(eng, bEnv, tEnv, ""));
            log.info(responseJson.toString());
            return responseJson;
        } catch (SQLException sqle) {
            responseJson.fail(999, "数据库比对失败！原因：" + sqle.getMessage());
            log.info(responseJson.toString(), sqle);
            return responseJson;
        } catch (Exception e) {
            responseJson.fail(999, "数据库比对失败！原因：" + e.getMessage());
            log.info(responseJson.toString(), e);
            return responseJson;
        }
    }
}
