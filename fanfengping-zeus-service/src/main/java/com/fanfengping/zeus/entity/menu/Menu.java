package com.fanfengping.zeus.entity.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private Integer id;	        // 系统主键
    private String title;	    // 菜单名称
    private String icon;	    // 菜单图标
    private String path;	    // 菜单路径（配置路径即可）
    private Integer level;	    // 菜单级别（1，一级菜单；2，二级菜单；3，三级菜单）
    private Integer menuId;	    // 上级菜单ID
    private Integer dis;	    // 是否显示（默认不显示。1，显示；0，不显示。）
    private Integer disorder;	// 显示顺序（数字小，则优先显示）
    private String operator;	// 操作人
    private String ctime;	    // 创建时间
    private String utime;	    // 更新时间

    public Menu(String title, String icon, String path, Integer level, Integer menuId,
                Integer dis, Integer disorder, String operator) {
        this.title = title;
        this.icon = icon;
        this.path = path;
        this.level = level;
        this.menuId = menuId;
        this.dis = dis;
        this.disorder = disorder;
        this.operator = operator;
    }
}
