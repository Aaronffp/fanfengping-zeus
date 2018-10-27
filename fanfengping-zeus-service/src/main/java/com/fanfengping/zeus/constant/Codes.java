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
     * 数据管理
     */
    String DATABASE = "DATABASE";
    String DATABASE_INSERT = "DATABASE_INSERT";
    String DATABASE_SEARCH = "DATABASE_SEARCH";
    String DATABASE_UPDATE = "DATABASE_UPDATE";
    String DATABASE_DELETE = "DATABASE_DELETE";
    String DATABASE_BENCHMARK = "DATABASE_BENCHMARK";
    String DATABASE_COMPARE = "DATABASE_COMPARE";

    /**
     * 持续集成
     */
    String APPS = "APPS";
    String APPS_INSERT = "APPS_INSERT";
    String APPS_SEARCH = "APPS_SEARCH";
    String APPS_UPDATE = "APPS_UPDATE";
    String APPS_DELETE = "APPS_DELETE";

    String SERV = "SERV";
    String SERV_INSERT = "SERV_INSERT";
    String SERV_SEARCH = "SERV_SEARCH";
    String SERV_UPDATE = "SERV_UPDATE";
    String SERV_DELETE = "SERV_DELETE";

    String BUILD = "BUILD";
    String BUILD_INSERT = "BUILD_INSERT";
    String BUILD_SEARCH = "BUILD_SEARCH";
    String BUILD_UPDATE = "BUILD_UPDATE";
    String BUILD_DELETE = "BUILD_DELETE";

    String DICTIONARY = "DICTIONARY";
    String DICTIONARY_INSERT = "DICTIONARY_INSERT";
    String DICTIONARY_SEARCH = "DICTIONARY_SEARCH";
    String DICTIONARY_UPDATE = "DICTIONARY_UPDATE";
    String DICTIONARY_DELETE = "DICTIONARY_DELETE";

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
