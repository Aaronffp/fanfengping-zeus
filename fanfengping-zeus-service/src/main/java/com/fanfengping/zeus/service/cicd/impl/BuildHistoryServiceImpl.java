package com.fanfengping.zeus.service.cicd.impl;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.BuildHistory;
import com.fanfengping.zeus.repository.cicd.BuildHistoryRepository;
import com.fanfengping.zeus.service.cicd.BuildHistoryService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BuildHistoryServiceImpl implements BuildHistoryService {
    @Autowired
    BuildHistoryRepository buildHistoryRepository;

    public ResponseJson add(BuildHistory buildHistory) {
        ResponseJson responseJson = new ResponseJson(Codes.BUILD, Codes.BUILD_INSERT).data("requestParams", buildHistory);

        try {
            if (buildHistory == null) {
                responseJson.fail(999, "新增失败！原因：新增构建历史信息为空或null");
                log.warn(responseJson.toString());
                return responseJson;
            }

            if (buildHistoryRepository.add(buildHistory) > 0) {
                responseJson.succ(200, "成功新增");
                log.info(responseJson.toString());
            } else {
                responseJson.fail(999, "新增失败！");
                log.error(responseJson.toString());
            }
        } catch (DuplicateKeyException dke) {
            responseJson.fail(999, "新增构建历史信息失败，系统异常！原因：" + dke.getMessage());
            log.error(responseJson.toString(), dke);
            return responseJson;
        } catch (Exception e) {
            responseJson.fail(999, "新增构建历史信息失败，系统异常！原因：" + e.getMessage());
            log.error(responseJson.toString(), e);
            return responseJson;
        }

        return responseJson;
    }

    public ResponseJson update(BuildHistory buildHistory) {
        ResponseJson responseJson = new ResponseJson(Codes.BUILD, Codes.BUILD_UPDATE).data("requestParams", buildHistory);
        return responseJson.succ(200, "构建历史不提供更新功能！请知悉！");
    }

    public ResponseJson delete(BuildHistory buildHistory) {
        ResponseJson responseJson = new ResponseJson(Codes.BUILD, Codes.BUILD_DELETE).data("requestParams", buildHistory);
        return responseJson.succ(200, "构建历史不提供删除功能！请知悉！");
    }

    public ResponseJson findAllByConditions(String eng, String publish, String addr, String gitUrl, String gitBranch, String operator) {
        ResponseJson responseJson = new ResponseJson(Codes.BUILD, Codes.BUILD_SEARCH);
        return responseJson.succ(200, "成功查询！").data(buildHistoryRepository.findAllByConditions(eng, publish, addr, gitUrl, gitBranch, operator));
    }
}
