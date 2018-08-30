package com.fanfengping.zeus.entity.cicd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuildHistory implements Serializable {
    private int id;
    private String env;
    private String addr;
    private String eng;
    private String tag;
    private String release;
    private String gitUrl;
    private String gitBranch;
    private String link;
    private String operator;
    private String ctime;

    public BuildHistory(String env, String addr, String eng, String tag, String release,
                        String gitUrl, String gitBranch, String link, String operator) {
        this.env = env;
        this.addr = addr;
        this.eng = eng;
        this.tag = tag;
        this.release = release;
        this.gitUrl = gitUrl;
        this.gitBranch = gitBranch;
        this.link = link;
        this.operator = operator;
    }
}
