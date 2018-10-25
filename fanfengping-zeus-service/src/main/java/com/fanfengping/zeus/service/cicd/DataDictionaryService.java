package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.DataDictionary;
import com.fanfengping.zeus.util.ResponseJson;

public interface DataDictionaryService {
    Integer add(DataDictionary dataDictionary);
    ResponseJson findAllByConditions(String env, String eng, String url, String tableName, String columnName);
}
