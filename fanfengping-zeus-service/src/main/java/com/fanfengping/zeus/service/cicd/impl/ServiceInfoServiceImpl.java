package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import com.fanfengping.zeus.repository.cicd.ServiceInfoRepository;
import com.fanfengping.zeus.service.cicd.ServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInfoServiceImpl implements ServiceInfoService {
    @Autowired
    ServiceInfoRepository serviceInfoRepository;

    public ServiceInfo add(ServiceInfo serviceInfo) {
        if (serviceInfo != null && serviceInfoRepository.add(serviceInfo) > 0) {
            return serviceInfo;
        }

        return null;
    }

    public ServiceInfo update(ServiceInfo serviceInfo) {
        if (serviceInfo != null && serviceInfoRepository.update(serviceInfo) > 0) {
            return serviceInfo;
        }

        return null;
    }

    public ServiceInfo delete(ServiceInfo serviceInfo) {
        if (serviceInfo != null && serviceInfoRepository.delete(serviceInfo) > 0) {
            return serviceInfo;
        }

        return null;
    }

    @Override
    public List<ServiceInfo> findAllByConditions(String env, String eng, String chs, String updater) {
        return serviceInfoRepository.findAllByConditions(env, eng, chs, updater);
    }
}

