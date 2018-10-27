package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import com.fanfengping.zeus.util.ResponseJson;

public interface DatabaseCompareResultService {
    Integer add(DatabaseCompareResult databaseCompareResult);
    ResponseJson findAllByConditions(String eng, String targetEnv);
    ResponseJson compare(String eng, String env);
}
