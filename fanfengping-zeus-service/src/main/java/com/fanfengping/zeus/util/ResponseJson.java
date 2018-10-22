package com.fanfengping.zeus.util;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class ResponseJson extends LinkedHashMap<String, Object> {
    /////////////////////// 默认的键 ///////////////////////
    private static final String KEY_CODE = "code";       // 执行结果代码
    private static final String KEY_SUCC = "success";    // 执行结果状态
    private static final String KEY_MESS = "msg";        // 执行结果消息
    private static final String KEY_MENU = "menu";       // 操作菜单名称
    private static final String KEY_ACTI = "action";     // 操作动作
    private static final String KEY_OPER = "operator";   // 操作人
    private static final String KEY_TIME = "datetime";   // 操作时间
    private static final String KEY_DATA = "data";       // 响应数据

    /////////////////////// 默认的值 ///////////////////////
    private static final int     DEFAULT_CODE_SUCC = 200;            // 执行结果代码
    private static final int     DEFAULT_CODE_FAIL = -1;             // 执行结果代码
    private static final boolean DEFAULT_SUCC_SUCC = true;           // 执行成功状态
    private static final boolean DEFAULT_SUCC_FAIL = false;          // 执行失败状态
    private static final String  DEFAULT_MESS_SUCC = "success";      // 执行成功消息
    private static final String  DEFAULT_MESS_FAIL = "fail";         // 执行失败消息
    private static final String  DEFAULT_MENU_VAL  = null;           // 操作菜单名称
    private static final String  DEFAULT_ACTI_VAL  = null;           // 操作动作
    private static final String  DEFAULT_OPER_VAL  = "ANON";         // 操作人
    private static final String  DEFAULT_TIME_VAL  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    private static final String  DEFAULT_DATA_VAL  = null;           // 响应数据

    /**
     * 全参构造函数
     * @param code
     * @param success
     * @param message
     * @param menu
     * @param action
     * @param operator
     * @param data
     */
    public ResponseJson(int code, boolean success, String message, String menu, String action, String operator, Object data){
        this.put(KEY_CODE, code);
        this.put(KEY_SUCC, success);
        this.put(KEY_MESS, message);
        this.put(KEY_MENU, menu);
        this.put(KEY_ACTI, action);
        this.put(KEY_OPER, operator);
        this.put(KEY_TIME, DEFAULT_TIME_VAL);
        this.put(KEY_DATA, data == null ? DEFAULT_DATA_VAL : data);
    }

    public ResponseJson(int code, boolean success, String message, String menu, String action, String operator){
        this.put(KEY_CODE, code);
        this.put(KEY_SUCC, success);
        this.put(KEY_MESS, message);
        this.put(KEY_MENU, menu);
        this.put(KEY_ACTI, action);
        this.put(KEY_OPER, operator);
        this.put(KEY_TIME, DEFAULT_TIME_VAL);
    }

    /////////////////////// 最通用的两个构造函数 ///////////////////////
    /**
     * 操作名称
     */
    public ResponseJson(String menu) {
        this.put(KEY_CODE, DEFAULT_CODE_SUCC);  // 200
        this.put(KEY_SUCC, DEFAULT_SUCC_SUCC);  // true
        this.put(KEY_MESS, DEFAULT_MESS_SUCC);  // success
        this.put(KEY_MENU, menu);               // menu
        this.put(KEY_ACTI, DEFAULT_ACTI_VAL);   // null
        this.put(KEY_OPER, DEFAULT_OPER_VAL);   // null
        this.put(KEY_TIME, DEFAULT_TIME_VAL);   // yyyy-MM-dd HH:mm:ss.SSS
        this.put(KEY_DATA, DEFAULT_DATA_VAL);   // null
    }

    /**
     * 操作名称、操作动作
     */
    public ResponseJson(String menu, String action) {
        this.put(KEY_CODE, DEFAULT_CODE_SUCC);  // 200
        this.put(KEY_SUCC, DEFAULT_SUCC_SUCC);  // true
        this.put(KEY_MESS, DEFAULT_MESS_SUCC);  // success
        this.put(KEY_MENU, menu);               // menu
        this.put(KEY_ACTI, action);   // null
        this.put(KEY_OPER, DEFAULT_OPER_VAL);   // null
        this.put(KEY_TIME, DEFAULT_TIME_VAL);   // yyyy-MM-dd HH:mm:ss.SSS
        this.put(KEY_DATA, DEFAULT_DATA_VAL);   // null
    }

    /**
     * 操作名称，操作动作，执行人
     */
    public ResponseJson(String menu, String action, String operator) {
        this.put(KEY_CODE, DEFAULT_CODE_SUCC);
        this.put(KEY_SUCC, DEFAULT_SUCC_SUCC);
        this.put(KEY_MESS, DEFAULT_MESS_SUCC);
        this.put(KEY_MENU, menu);
        this.put(KEY_ACTI, action);
        this.put(KEY_OPER, operator);
        this.put(KEY_TIME, DEFAULT_TIME_VAL);
        this.put(KEY_DATA, DEFAULT_DATA_VAL);
    }

    /**
     * 操作成功
     */
    public ResponseJson succ(String message) {
        this.put(KEY_MESS, message);
        return this;
    }

    public ResponseJson succ(Object data) {
        this.data(data);
        return this;
    }

    public ResponseJson succ(int code, String message) {
        this.put(KEY_CODE, code);
        this.put(KEY_MESS, message);
        return this;
    }

    public ResponseJson succ(String message, Object data) {
        this.put(KEY_MESS, message);
        this.data(data);
        return this;
    }

    public ResponseJson succ(String message, String dataKey, Object dataVal) {
        this.put(KEY_MESS, message);
        this.data(dataKey, dataVal);
        return this;
    }

    public ResponseJson succ(int code, String message, Object data) {
        this.put(KEY_CODE, code);
        this.put(KEY_MESS, message);
        this.data(data);
        return this;
    }

    public ResponseJson succ(int code, String message, String dataKey, Object dataVal) {
        this.put(KEY_CODE, code);
        this.put(KEY_MESS, message);
        this.data(dataKey, dataVal);
        return this;
    }


    /**
     * 操作判定失败的
     */
    public ResponseJson fail(String message) {
        this.put(KEY_CODE, DEFAULT_CODE_FAIL);
        this.put(KEY_SUCC, DEFAULT_SUCC_FAIL);
        this.put(KEY_MESS, message);
        return this;
    }

    public ResponseJson fail(int code, String message) {
        this.put(KEY_CODE, code);
        this.put(KEY_SUCC, DEFAULT_SUCC_FAIL);
        this.put(KEY_MESS, message);
        return this;
    }

    public ResponseJson fail(String message, Object data) {
        this.put(KEY_CODE, DEFAULT_CODE_FAIL);
        this.put(KEY_SUCC, DEFAULT_SUCC_FAIL);
        this.put(KEY_MESS, message);
        this.data(data);
        return this;
    }

    public ResponseJson fail(String message, String dataKey, Object dataVal) {
        this.put(KEY_CODE, DEFAULT_CODE_FAIL);
        this.put(KEY_SUCC, DEFAULT_SUCC_FAIL);
        this.put(KEY_MESS, message);
        this.data(dataKey, dataVal);
        return this;
    }

    public ResponseJson fail(int code, String message, Object data) {
        this.put(KEY_CODE, code);
        this.put(KEY_SUCC, DEFAULT_SUCC_FAIL);
        this.put(KEY_MESS, message);
        this.data(data);
        return this;
    }

    public ResponseJson fail(int code, String message, String dataKey, Object dataVal) {
        this.put(KEY_CODE, code);
        this.put(KEY_SUCC, DEFAULT_SUCC_FAIL);
        this.put(KEY_MESS, message);
        this.data(dataKey, dataVal);

        return this;
    }

    /**
     * 操作动态判定成功或失败
     */
    public static ResponseJson result(String operator, boolean success) {
        return new ResponseJson(
                success ? DEFAULT_CODE_SUCC : DEFAULT_CODE_FAIL,
                success ? DEFAULT_SUCC_FAIL : DEFAULT_SUCC_FAIL,
                success ? DEFAULT_MESS_SUCC : DEFAULT_MESS_FAIL,
                DEFAULT_MENU_VAL, DEFAULT_ACTI_VAL, operator, DEFAULT_DATA_VAL);
    }

    public static ResponseJson result(String operator, boolean success, Object data) {
        return new ResponseJson (
                success ? DEFAULT_CODE_SUCC : DEFAULT_CODE_FAIL,
                success ? DEFAULT_SUCC_FAIL : DEFAULT_SUCC_FAIL,
                success ? DEFAULT_MESS_SUCC : DEFAULT_MESS_FAIL,
                DEFAULT_MENU_VAL, DEFAULT_ACTI_VAL, operator, data);
    }

    public static ResponseJson result(String operator, boolean success, String dataKey, Object dataVal) {
        return new ResponseJson (
                success ? DEFAULT_CODE_SUCC : DEFAULT_CODE_FAIL,
                success ? DEFAULT_SUCC_FAIL : DEFAULT_SUCC_FAIL,
                success ? DEFAULT_MESS_SUCC : DEFAULT_MESS_FAIL,
                DEFAULT_MENU_VAL, DEFAULT_ACTI_VAL, operator, DEFAULT_DATA_VAL)
                .data(dataKey, dataVal);
    }

    /////////////////////// 各种链式调用方法 ///////////////////////
    /**
     * 设置执行结果代码
     * @param code
     * @return
     */
    public ResponseJson code(int code) {
        this.put(KEY_CODE, code);
        return this;
    }

    /**
     * 设置执行结果状态
     * @param success
     * @return
     */
    public ResponseJson succ(boolean success){
        this.put(KEY_SUCC, success);
        return this;
    }

    /**
     * 设置执行结果信息
     * @param message
     * @return
     */
    public ResponseJson mess(String message){
        this.put(KEY_MESS, message);
        return this;
    }

    /**
     * 设置操作菜单名称
     * @param menu
     * @return
     */
    public ResponseJson menu(String menu){
        this.put(KEY_MENU, menu);
        return this;
    }

    /**
     * 设置操作动作
     * @param action
     * @return
     */
    public ResponseJson acti(String action){
        this.put(KEY_ACTI, action);
        return this;
    }

    /**
     * 设置执行操作人
     * @param operator
     * @return
     */
    public ResponseJson oper(String operator){
        this.put(KEY_OPER, operator);
        return this;
    }

    /**
     * 设置响应数据
     * @param dataVal
     * @return
     */
    public ResponseJson data(Object dataVal){
        this.put(KEY_DATA, dataVal);
        return this;
    }

    /**
     * 设置操作返回的数据，数据使用自定义的key存储
     * @param dataKey
     * @param dataVal
     * @return
     */
    public ResponseJson data(String dataKey, Object dataVal){
        this.put(dataKey, dataVal);
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
}
