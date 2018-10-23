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

    public List<DataDictionary> findAllByConditions(String env, String eng, String url, String tableName, String columnName) {
        ResponseJson responseJson = new ResponseJson(Codes.DICTIONARY, Codes.DICTIONARY_SEARCH);
        List<DataDictionary> dataDictionaryList = dataDictionaryRepository.findAllByConditions(env, eng, url, tableName, columnName);

        // 存在半小时内数据
        if (dataDictionaryList.size() > 0) {
            responseJson.succ(200, "已存在半小时内数据").data(dataDictionaryList);
            log.info(responseJson.toString());
            return dataDictionaryList;
        }

        Database db = databaseRepository.findByEnvAndEng(env, eng);
        if (db != null) {
            responseJson.succ(200, "开始生成数据库字典信息").data(db);
            log.info(responseJson.toString());
            mysqlUtil.genDataDictionary(db);
            responseJson.succ(200, "成功生成数据库字典信息").data(db);
            log.info(responseJson.toString());
        }

        return dataDictionaryRepository.findAllByConditions(env, eng, url, tableName, columnName);
    }
}
