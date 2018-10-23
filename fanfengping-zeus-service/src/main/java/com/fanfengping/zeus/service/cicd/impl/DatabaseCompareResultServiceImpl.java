package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.repository.cicd.DatabaseCompareResultRepository;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DatabaseCompareResultService;
import com.fanfengping.zeus.util.MysqlUtil;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class DatabaseCompareResultServiceImpl implements DatabaseCompareResultService {
    @Autowired
    DatabaseCompareResultRepository databaseCompareResultRepository;

    @Autowired
    DatabaseRepository databaseRepository;

    @Autowired
    MysqlUtil mysqlUtil;

    public Integer add(DatabaseCompareResult databaseCompareResult) {
        return databaseCompareResultRepository.add(databaseCompareResult);
    }

    public List<DatabaseCompareResult> findAllByConditions(String eng, String env) {
        return databaseCompareResultRepository.findAllByConditions(eng, env);
    }

    public ResponseJson compare(String eng, String env) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_COMPARE);

        try {
            if (databaseCompareResultRepository.exist(eng, env).size() > 0) {
                responseJson.succ(200, "数据库表结构信息已存在半小时内比对结果，敬请查询；或半小时后重新比对!");
                return responseJson;
            }

            databaseCompareResultRepository.delete(eng, env);

            List<Database> dbs = databaseRepository.findAllBenchmark(eng);

            long start = System.currentTimeMillis();

            if (dbs.size() < 1) {
                responseJson.succ(200, "未存在基准库，请返回数据库列表页面确认！");
                return responseJson;
            }

            for (Database ds : dbs) {
                List<Database> dbt = databaseRepository.findAllComp(ds.getEng(), env);

                if (dbt.size() < 1) {
                    continue;
                }

                for (Database dt : dbt) {
                    String timestamp = String.valueOf(System.currentTimeMillis());

                    DatabaseCompareResult databaseCompareResult = new DatabaseCompareResult(timestamp, 0, ds.getEng(), ds.getId(), ds.getEnv(), ds.getUrl(), dt.getId(), dt.getEnv(), dt.getUrl(), "开始比对数据库...", "");

                    databaseCompareResultRepository.add(databaseCompareResult);
                    log.info(String.format("[基准库：%s - %s：%s] - 开始比对数据库...", ds.getUrl(), dt.getEnv(), dt.getUrl()));

                    mysqlUtil.compareAllTables(ds, dt, timestamp);
                }
            }

            long end = System.currentTimeMillis();

            responseJson.succ(200, String.format("数据库比对完成，总计耗时：%d 分 %d 秒", (end - start)/1000/60, (end - start)/1000 % 60));
            return responseJson;
        } catch (SQLException sqle) {
            responseJson.fail(999, sqle.getMessage());
            log.info(responseJson.toString(), sqle);
            return responseJson;
        } catch (Exception e) {
            responseJson.fail(999, e.getMessage());
            log.info(responseJson.toString(), e);
            return responseJson;
        }
    }
}
