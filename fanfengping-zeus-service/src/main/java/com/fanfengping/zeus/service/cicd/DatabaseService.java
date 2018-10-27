package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.service.BaseService;
import com.fanfengping.zeus.util.ResponseJson;

import java.util.List;

public interface DatabaseService extends BaseService<Database> {
    ResponseJson findAllByConditions(String env, String valid, String eng, String chs);
}
