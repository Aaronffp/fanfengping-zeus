package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import com.fanfengping.zeus.service.cicd.ServiceInfoService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app")
public class ServiceInfoController {
    @Autowired
    ServiceInfoService serviceInfoService;

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson add(@RequestBody ServiceInfo serviceInfo) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_INSERT).data("requestParams", serviceInfo);

        if (serviceInfo == null) {
            responseJson.succ(999, "新增失败！原因：服务信息为空！");
            log.warn(responseJson.toString());
            return responseJson;
        }

        return serviceInfoService.add(serviceInfo);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson update(@RequestBody ServiceInfo serviceInfo) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_UPDATE).data("requestParams", serviceInfo);

        if (serviceInfo == null) {
            responseJson.fail(999, "更新失败！原因：服务信息为空！");
            log.warn(responseJson.toString());
            return responseJson;
        }

        return serviceInfoService.update(serviceInfo);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson delete(@RequestBody ServiceInfo serviceInfo) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_DELETE).data("requestParams", serviceInfo);

        if (serviceInfo == null) {
            responseJson.fail(200, "成功删除！");
            log.info(responseJson.toString());
            return responseJson;
        }

        return serviceInfoService.delete(serviceInfo);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseJson findAllByConditions(@RequestParam String env,
                                            @RequestParam String eng,
                                            @RequestParam String chs,
                                            @RequestParam String url,
                                            @RequestParam String updater) {
        return serviceInfoService.findAllByConditions(env, eng, chs, url, updater);
    }
}
