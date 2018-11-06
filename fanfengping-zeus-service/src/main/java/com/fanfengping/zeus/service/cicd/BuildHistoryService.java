package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.BuildHistory;
import com.fanfengping.zeus.service.BaseService;
import com.fanfengping.zeus.util.ResponseJson;

public interface BuildHistoryService extends BaseService<BuildHistory> {
    ResponseJson findAllByConditions(String eng, String publish, String addr, String gitUrl, String gitBranch, String operator);
}
