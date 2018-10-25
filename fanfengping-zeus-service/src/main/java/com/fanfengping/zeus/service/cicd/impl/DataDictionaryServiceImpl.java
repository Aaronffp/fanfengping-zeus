package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.DataDictionary;
import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.repository.cicd.DataDictionaryRepository;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DataDictionaryService;
import com.fanfengping.zeus.util.MysqlUtil;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Autowired
    DatabaseRepository databaseRepository;
    @Autowired
    DataDictionaryRepository dataDictionaryRepository;
    @Autowired
    MysqlUtil mysqlUtil;

    @Override
    public Integer add(DataDictionary dataDictionary) {
        return dataDictionaryRepository.add(dataDictionary);
    }

    public ResponseJson findAllByConditions(String env, String eng, String url, String tableName, String columnName) {
        ResponseJson responseJson = new ResponseJson(Codes.DICTIONARY, Codes.DICTIONARY_SEARCH);
        List<DataDictionary> dataDictionaryList = dataDictionaryRepository.findAllByConditions(env, eng, url, tableName, columnName);

        // 存在半小时内数据
        if (dataDictionaryList.size() > 0) {
            responseJson.succ(200, "成功查询").data(dataDictionaryList);
            log.info(responseJson.toString());
            return responseJson;
        }

        Database db = databaseRepository.findByEnvAndEng(env, eng);

        if (db != null && "mysql".equals(db.getType().toLowerCase()) && !"docker".equals(db.getEnv().toLowerCase())) {
            responseJson = mysqlUtil.genDataDictionary(db);

            if ("200".equals(responseJson.get("code"))) {
                responseJson.data(dataDictionaryRepository.findAllByConditions(env, eng, url, tableName, columnName));
                log.info(responseJson.toString());
                return responseJson;
            }

            return responseJson;
        }

        return responseJson.fail(999, "数据库字典查询失败！原因：数据库必须为MySQL且数据库必须可连通。").data("source", db);
    }
}
