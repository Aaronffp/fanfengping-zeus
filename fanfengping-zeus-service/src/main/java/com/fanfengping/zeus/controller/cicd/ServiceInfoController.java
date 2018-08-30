package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import com.fanfengping.zeus.service.cicd.ServiceInfoService;
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
    public ServiceInfo add(@RequestBody ServiceInfo serviceInfo) {
        return serviceInfoService.add(serviceInfo);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ServiceInfo update(ServiceInfo serviceInfo) {
        return serviceInfoService.update(serviceInfo);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ServiceInfo delete(ServiceInfo serviceInfo) {
        return serviceInfoService.delete(serviceInfo);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ServiceInfo> findAllByConditions(@RequestParam String env,
                                                 @RequestParam String eng,
                                                 @RequestParam String chs,
                                                 @RequestParam String updater) {
        return serviceInfoService.findAllByConditions(env, eng, chs, updater);
    }
}
