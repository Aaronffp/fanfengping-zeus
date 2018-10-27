package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import com.fanfengping.zeus.service.BaseService;
import com.fanfengping.zeus.util.ResponseJson;

import java.util.List;

public interface ServiceInfoService extends BaseService<ServiceInfo> {
    ResponseJson findAllByConditions(String env, String eng, String chs, String url, String updater);
}
