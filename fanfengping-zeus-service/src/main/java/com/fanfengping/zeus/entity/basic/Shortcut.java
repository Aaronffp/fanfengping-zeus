package com.fanfengping.zeus.entity.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Shortcut implements Serializable {
    private int id;               // 系统主键
    private int dis;              // 是否显示[1, 显示；0, 不显示（默认）]
    private int disorder;         // 显示顺序（小数字在前）
    private String  name;         // 网站名称
    private String  icon;         // 网站图标链接（可为空，默认网站图标）
    private String  url;          // 网站链接
    private String  manager;      // 网站管理员
    private String  mobile;       // 手机号码
    private String  operator;     // 更新人
    private String  ctime;        // 创建日期
    private String  utime;        // 更新日期

    public Shortcut(int dis, int disorder, String name, String icon, String url,
                    String manager, String mobile, String operator) {
        this.dis = dis;
        this.disorder = disorder;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.manager = manager;
        this.mobile = mobile;
        this.operator = operator;
    }
}
