package com.fanfengping.zeus.service.cicd.impl;


import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DatabaseService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    DatabaseRepository databaseRepository;

    public ResponseJson add(Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_INSERT).data("requestParams", database);

        try{
            if (databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng()) != null) {
                responseJson.fail(999, "新增失败！原因：已存在同环境同英文简称的数据库。");
                log.error(responseJson.toString());
                return responseJson;
            }

            if (databaseRepository.add(database) > 0) {
                if (database.getBenchmark() == 1) {
                    databaseRepository.setBenchmarkFalse(database);
                    responseJson.succ(200, "更新其他数据库为非基准库！");
                    log.info(responseJson.toString());
                }

                responseJson.succ(200, "成功新增！").data(databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng()));
                log.info(responseJson.toString());
                return responseJson;
            }

            responseJson.fail(999, "新增失败！");
            log.error(responseJson.toString());
        } catch (Exception e) {
            responseJson.fail(999, "新增失败！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson update(Database database) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_UPDATE).data("requestParams", database);

        try{
            if (databaseRepository.update(database) > 0) {
                if (database.getBenchmark() == 1) {
                    databaseRepository.setBenchmarkFalse(database);
                    responseJson.succ(200, "更新其他数据库为非基准库");
                    log.info(responseJson.toString());
                }

                responseJson.succ(200, "成功更新！").data(databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng()));
                log.info(responseJson.toString());
                return responseJson;
            }

            responseJson.fail(999, "更新失败！");
            log.error(responseJson.toString());
        } catch (Exception e) {
            responseJson.fail(999, "更新失败！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson delete(Database db) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_DELETE).data("requestParams", db);

        try{
            if (databaseRepository.findAllByConditions(db.getEnv(), "" + db.getValid(), db.getEng(), db.getChs()) == null) {
                responseJson.succ(200, "成功删除！");
                log.info(responseJson.toString());
                return responseJson;
            }

            databaseRepository.delete(db);
            responseJson.succ(200, "成功删除！");
            log.info(responseJson.toString());
        } catch (Exception e) {
            responseJson.fail(999, "删除失败！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson findAllByConditions(String env, String valid, String eng, String chs) {
        ResponseJson responseJson = new ResponseJson(Codes.DATABASE, Codes.DATABASE_SEARCH);
        return responseJson.succ(200, "成功查询！").data(databaseRepository.findAllByConditions(env, valid, eng, chs));
    }
}

