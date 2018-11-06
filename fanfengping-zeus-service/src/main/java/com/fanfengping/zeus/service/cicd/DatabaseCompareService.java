package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.DatabaseCompare;
import com.fanfengping.zeus.util.ResponseJson;

public interface DatabaseCompareService {
    Integer add(DatabaseCompare databaseCompare);
    ResponseJson findAllByConditions(String eng, String bEnv, String tEnv, String tableName);
    ResponseJson compare(String eng, String bEnv, String tEnv);
}
