package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import com.fanfengping.zeus.repository.cicd.ServiceInfoRepository;
import com.fanfengping.zeus.service.cicd.ServiceInfoService;
import com.fanfengping.zeus.util.ResponseJson;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ServiceInfoServiceImpl implements ServiceInfoService {
    @Autowired
    ServiceInfoRepository serviceInfoRepository;

    public ResponseJson add(ServiceInfo serviceInfo) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_INSERT).data("requestParams", serviceInfo);

        if (serviceInfoRepository.findAllByEnvAndEngAndUrl(serviceInfo) == null) {
            try {
                if (serviceInfoRepository.add(serviceInfo) > 0) {
                    responseJson.succ(200, "成功新增").data(serviceInfoRepository.findAllByEnvAndEngAndUrl(serviceInfo));
                    log.info(responseJson.toString());
                } else {
                    responseJson.fail(999, "新增失败！");
                    log.error(responseJson.toString());
                }
            } catch (DuplicateKeyException dke) {
                responseJson.fail(999, "新增服务信息失败！原因：" + dke.getMessage());
                log.error(responseJson.toString(), dke);
                return responseJson;
            } catch (Exception e) {
                responseJson.fail(999, "系统异常！原因：" + e.getMessage());
                log.error(responseJson.toString(), e);
                return responseJson;
            }
        } else {
            responseJson.succ(999, String.format("新增失败！原因：已存在同环境下同英文简称同访问连接的服务信息！"));
            log.warn(responseJson.toString());
        }

        return responseJson;
    }

    public ResponseJson update(ServiceInfo serviceInfo) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_UPDATE).data("requestParams", serviceInfo);

        if (serviceInfoRepository.update(serviceInfo) > 0) {
            responseJson.succ(200, "成功更新").data(serviceInfoRepository.findAllByEnvAndEngAndUrl(serviceInfo));
            log.info(responseJson.toString());
        } else {
            responseJson.fail(999, "更新失败！");
            log.error(responseJson.toString());
        }

        return responseJson;
    }

    public ResponseJson delete(ServiceInfo serviceInfo) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_DELETE).data("requestParams", serviceInfo);

        try {
            // 数据库不存在
            if (serviceInfoRepository.findAllByEnvAndEngAndUrl(serviceInfo) == null) {
                responseJson.succ(200, "成功删除！");
                log.info(responseJson.toString());
                return responseJson;
            }

            serviceInfoRepository.delete(serviceInfo);
            responseJson.succ(200, "成功删除！");
            log.info(responseJson.toString());
        } catch (Exception e) {
            responseJson.fail(999, "删除失败！原因：" + e.getMessage());
            log.error(responseJson.toString());
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson findAllByConditions(String env, String url, String eng, String chs, String updater) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_SEARCH);
        return responseJson.succ(200, "成功查询！").data(serviceInfoRepository.findAllByConditions(env, url, eng, chs, updater));
    }
}

