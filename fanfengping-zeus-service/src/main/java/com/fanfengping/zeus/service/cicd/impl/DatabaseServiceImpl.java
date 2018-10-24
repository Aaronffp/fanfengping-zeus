package com.fanfengping.zeus.service.cicd.impl;


import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DatabaseService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    DatabaseRepository databaseRepository;

    public Database add(Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_INSERT);

        try{
            if (databaseRepository.add(database) > 0) {
                responseJson.succ(200, "成功新增").data(database);
                log.info(responseJson.toString());

                if (database.getBenchmark() == 1) {
                    databaseRepository.setBenchmarkFalse(database);
                    responseJson.succ(200, "更新其他数据库为非基准库").data(database);
                    log.info(responseJson.toString());
                }

                return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
            }

            responseJson.fail(999, "新增数据库失败").data(database);
            log.error(responseJson.toString());
            return null;
        } catch (Exception e) {
            responseJson.fail(999, "新增数据库异常：" + e.getMessage()).data(database);
            log.error(responseJson.toString(), e);
            return null;
        }
    }

    public Database update(Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_UPDATE);

        try{
            if (databaseRepository.update(database) > 0) {
                responseJson.succ(200, "成功更新").data(database);
                log.info(responseJson.toString());

                if (database.getBenchmark() == 1) {
                    databaseRepository.setBenchmarkFalse(database);
                    responseJson.succ(200, "更新其他数据库为非基准库").data(database);
                    log.info(responseJson.toString());
                }

                return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
            }

            responseJson.fail(999, "更新数据库失败").data(database);
            log.error(responseJson.toString());
            return null;
        } catch (Exception e) {
            responseJson.fail(999, "更新数据库异常：" + e.getMessage()).data(database);
            log.error(responseJson.toString(), e);
            return null;
        }
    }

    public Database delete(Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_DELETE);

        try{
            databaseRepository.delete(database);
            Database db = databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
            if ( db == null) {
                responseJson.succ(200, "成功删除").data(database);
                log.info(responseJson.toString());
                return null;
            }

            responseJson.fail(999, "删除数据库失败").data(database);
            log.error(responseJson.toString());
            return database;
        } catch (Exception e) {
            responseJson.fail(999, "删除数据库异常：" + e.getMessage()).data(database);
            log.error(responseJson.toString(), e);
            return database;
        }
    }

    public List<Database> findAllByConditions(String env, String valid, String eng, String chs) {
        return databaseRepository.findAllByConditions(env, valid, eng, chs);
    }

    public Database setBenchmark(Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_BENCHMARK);
        try{
            if (databaseRepository.setBenchmark(database) > 0) {
                databaseRepository.setBenchmarkFalse(database);
                responseJson.succ(200, "基准库设置成功").data(database);
                log.info(responseJson.toString());
                return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
            }

            responseJson.fail(999, "基准库设置失败").data(database);
            log.warn(responseJson.toString());
            return null;
        } catch (Exception e) {
            responseJson.fail(999, "基准库设置异常：" + e.getMessage()).data(database);
            log.error(responseJson.toString(), e);
            return null;
        }
    }
}

