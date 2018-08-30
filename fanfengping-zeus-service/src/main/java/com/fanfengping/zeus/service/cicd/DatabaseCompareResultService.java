package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;

import java.util.List;

public interface DatabaseCompareResultService {
    DatabaseCompareResult add(DatabaseCompareResult databaseCompareResult);
    List<DatabaseCompareResult> findAllByConditions(String eng, String targetEnv);
    String compare(String eng, String env);
}
