package com.fanfengping.zeus.service.cicd;

import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import com.fanfengping.zeus.service.BaseService;

import java.util.List;

public interface ServiceInfoService extends BaseService<ServiceInfo> {
    List<ServiceInfo> findAllByConditions(String env, String eng, String chs, String updater);
}
