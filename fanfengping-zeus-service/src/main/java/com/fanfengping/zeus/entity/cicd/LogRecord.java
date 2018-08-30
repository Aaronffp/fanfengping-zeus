package com.fanfengping.zeus.entity.cicd;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogRecord implements Serializable {
    private int id;
    private String timestamp;
    private long duration;
    private String level;
    private String threadname;
    private String message;
    private String pack;
    private String clazz;
    private String method;
    private int linenumber;
    private String fullinfo;
    private String debug;
    private String ctime;

    public LogRecord(String timestamp, long duration, String level, String threadname, String message,
                     String pack, String clazz, String method, int linenumber, String fullinfo, String debug) {
        this.timestamp = timestamp;
        this.duration = duration;
        this.level = level;
        this.threadname = threadname;
        this.message = message;
        this.pack = pack;
        this.clazz = clazz;
        this.method = method;
        this.linenumber = linenumber;
        this.fullinfo = fullinfo;
        this.debug = debug;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
