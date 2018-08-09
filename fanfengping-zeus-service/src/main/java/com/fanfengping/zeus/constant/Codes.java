package com.fanfengping.zeus.constant;

public interface Codes {
    /**
     * 操作名称、动作
     */
    String USER        = "USER";
    String USER_LOGIN  = "USER_LOGIN";
    String USER_INSERT = "USER_INSERT";
    String USER_SEARCH = "USER_SEARCH";
    String USER_UPDATE = "USER_UPDATE";
    String USER_DELETE = "USER_DELETE";

    /**
     * 服务、服务状态
     */
    String SERVICE        = "SERVICE";
    String SERVICE_STATUS = "SERVICE_STATUS";

    /**
     * 长度校验相关
     */
    int LENGTH_USER_ACCOUNT_MIN = 5;
    int LENGTH_USER_ACCOUNT_MAX = 50;
    int LENGTH_USER_PASSWORD_MIN = 8;
    int LENGTH_USER_PASSWORD_MAX = 50;

    /**
     * 未登录
     */
    int    UNAUTHEN_CODE = 401;
    String UNAUTHEN_DESC = "您未登陆，请先登陆。";
    String UNAUTHEN_ANON = "仅允许访客或匿名用户访问。若您已登陆，请先退出。";

    /**
     * 未授权，拒绝访问
     */
    int    UNAUTHZ_CODE  = 403;
    String UNAUTHZ_DESC  = "未授权，拒绝访问。";

    /**
     * shiro相关的错误
     */
    int    SHIRO_ERR     = 444;
    String SHIRO_DESC    = "Shiro 鉴权或授权过程中出现错误";

    /**
     * 服务端异常
     */
    int    SERVER_ERR    = 500;
    String SERVER_DESC   = "服务端异常";
}
