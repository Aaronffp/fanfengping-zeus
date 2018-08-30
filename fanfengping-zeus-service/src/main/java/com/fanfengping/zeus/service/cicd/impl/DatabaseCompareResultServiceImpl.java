package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.repository.cicd.DatabaseCompareResultRepository;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DatabaseCompareResultService;
import com.fanfengping.zeus.util.MysqlUtil;
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

    public DatabaseCompareResult add(DatabaseCompareResult databaseCompareResult) {
        if (databaseCompareResult != null && databaseCompareResultRepository.add(databaseCompareResult) > 0) {
            return databaseCompareResultRepository.findByEngAndTargerEnv(databaseCompareResult.getEng(), databaseCompareResult.getTargetEnv());
        }
        return null;
    }

    public List<DatabaseCompareResult> findAllByConditions(String eng, String env) {
        return databaseCompareResultRepository.findAllByConditions(eng, env);
    }

    public String compare(String eng, String env) {
        try {
            if (databaseCompareResultRepository.exist(eng, env).size() > 0) {
                return "{\"status\":\"OK\", \"message\":\"数据库表结构信息已存在半小时内比对结果，敬请查询；或半小时后重新比对!\"}";
            }

            databaseCompareResultRepository.delete(eng, env);

            List<Database> dbs = databaseRepository.findAllBenchmark(eng);

            long start = System.currentTimeMillis();

            if (dbs.size() < 1) {
                return "";
            }

            for (Database ds : dbs) {
                List<Database> dbt = databaseRepository.findAllComp(ds.getEng(), env);

                if (dbt.size() < 1) {
                    continue;
                }

                for (Database dt : dbt) {
                    String timestamp = String.valueOf(System.currentTimeMillis());

                    DatabaseCompareResult compInfo = new DatabaseCompareResult(timestamp, 0, ds.getEng(), ds.getId(), ds.getEnv(), ds.getUrl(), dt.getId(), dt.getEnv(), dt.getUrl(), "开始比对数据库...", "");

                    databaseCompareResultRepository.add(compInfo);
                    System.out.println(String.format("[基准库：%s - 比对库：%s] - 开始比对数据库...", ds.getUrl(), dt.getUrl()));

                    mysqlUtil.compareAllTables(ds, dt, timestamp);
                }
            }

            long end = System.currentTimeMillis();

            return String.format("{\"status\":\"OK\", \"message\":\"数据库比对完成，总计耗时：%d分%d秒\"}", (end - start)/1000/60, (end - start)/1000 % 60);
        } catch (SQLException sqle) {
            log.error(sqle.getMessage(), sqle);
            return null;
            // TODO
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
            // TODO
        }
    }
}
