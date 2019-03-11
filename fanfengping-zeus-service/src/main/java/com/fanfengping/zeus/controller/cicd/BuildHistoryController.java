package com.fanfengping.zeus.controller.cicd;

import com.fanfengping.zeus.constant.Codes;
import com.fanfengping.zeus.controller.BaseController;
import com.fanfengping.zeus.entity.cicd.BuildHistory;
import com.fanfengping.zeus.service.cicd.BuildHistoryService;
import com.fanfengping.zeus.util.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cicd/build")
public class BuildHistoryController implements BaseController<BuildHistory> {
    @Autowired
    BuildHistoryService buildHistoryService;

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson add(@RequestBody BuildHistory buildHistory) {
        ResponseJson responseJson = new ResponseJson(Codes.BUILD, Codes.BUILD_INSERT);

        if (buildHistory == null) {
            responseJson.fail(999, "新增失败！原因：构建历史信息为空！");
            log.warn(responseJson.toString());
            return responseJson;
        }

        return buildHistoryService.add(buildHistory);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson update(@RequestBody BuildHistory buildHistory) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_UPDATE).data("requestParams", buildHistory);

        if (buildHistory == null) {
            responseJson.fail(999, "更新失败！原因：构建历史信息为空！");
            log.warn(responseJson.toString());
            return responseJson;
        }

        return buildHistoryService.update(buildHistory);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson delete(@RequestBody BuildHistory buildHistory) {
        ResponseJson responseJson = new ResponseJson(Codes.SERV, Codes.SERV_DELETE).data("requestParams", buildHistory);

        if (buildHistory == null) {
            responseJson.succ(200, "成功删除！");
            log.info(responseJson.toString());
            return responseJson;
        }

        return buildHistoryService.delete(buildHistory);
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseJson findAllByConditions(@RequestParam("eng") String eng,
                                            @RequestParam("status") String status,
                                            @RequestParam("gitUrl") String gitUrl,
                                            @RequestParam("gitBranch") String gitBranch,
                                            @RequestParam("operator") String operator) {
        return buildHistoryService.findAllByConditions(eng, status, gitUrl, gitBranch, operator);
    }
}
