package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    Integer add(DataDictionary dataDictionary);
    List<DataDictionary> findAllByConditions(String env, String eng, String url, String tableName, String columnName);
}
