package com.fanfengping.zeus.controller.basic;

import com.fanfengping.zeus.entity.basic.Shortcut;
import com.fanfengping.zeus.service.basic.ShortcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shortcut")
public class ShortcutController {
    @Autowired
    ShortcutService shortcutService;

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Shortcut add(@RequestBody Map<String, Object> reqMap) {
        int dis = Integer.parseInt(reqMap.get("dis").toString());
        int disorder = Integer.parseInt(reqMap.get("disorder").toString());
        String name = reqMap.get("name").toString();
        String icon = reqMap.get("icon").toString();
        String url = reqMap.get("url").toString();
        String manager = reqMap.get("manager").toString();
        String mobile = reqMap.get("mobile").toString();
        String operator = reqMap.get("operator").toString();

        Shortcut shortcut = new Shortcut(dis, disorder, name, icon, url, manager, mobile, operator);

        return shortcutService.add(shortcut);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Shortcut update(@RequestBody Map<String, Object> reqMap) {
        int id = Integer.parseInt(reqMap.get("id").toString());
        int dis = Integer.parseInt(reqMap.get("dis").toString());
        int disorder = Integer.parseInt(reqMap.get("disorder").toString());
        String name = reqMap.get("name").toString();
        String icon = reqMap.get("icon").toString();
        String url = reqMap.get("url").toString();
        String manager = reqMap.get("manager").toString();
        String mobile = reqMap.get("mobile").toString();
        String operator = reqMap.get("operator").toString();
        String ctime = reqMap.get("ctime").toString();
        String utime = reqMap.get("utime").toString();

        Shortcut shortcut = new Shortcut(id, dis, disorder, name, icon, url, manager, mobile, operator, ctime, utime);

        return shortcutService.update(shortcut);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Shortcut delete(@RequestBody Map<String, Object> reqMap) {
        int id = Integer.parseInt(reqMap.get("id").toString());
        int dis = Integer.parseInt(reqMap.get("dis").toString());
        int disorder = Integer.parseInt(reqMap.get("disorder").toString());
        String name = reqMap.get("name").toString();
        String icon = reqMap.get("icon").toString();
        String url = reqMap.get("url").toString();
        String manager = reqMap.get("manager").toString();
        String mobile = reqMap.get("mobile").toString();
        String operator = reqMap.get("operator").toString();
        String ctime = reqMap.get("ctime").toString();
        String utime = reqMap.get("utime").toString();

        Shortcut shortcut = new Shortcut(id, dis, disorder, name, icon, url, manager, mobile, operator, ctime, utime);

        return shortcutService.delete(shortcut);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Shortcut> findAll() {
        return shortcutService.findAll();
    }
}

