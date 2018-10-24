package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.service.BaseService;

import java.util.List;

public interface DatabaseService extends BaseService<Database> {
    Database setBenchmark(Database database);
    List<Database> findAllByConditions(String env, String valid, String eng, String chs);
}
