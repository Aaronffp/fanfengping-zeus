package com.fanfengping.zeus.handler;

import com.fanfengping.zeus.util.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 统一处理自定义异常捕获
 * 统一捕捉shiro的异常，返回给前台一个json信息，前台根据这个信息显示对应的提示，或者做页面的跳转。
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //不满足 @RequiresGuest 注解时抛出的异常信息
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";

    private ResponseJson responseJson = null;

//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseBody
//    public Json page401(UnauthenticatedException ue) {
//        String errorMsg = ue.getMessage();
//
//        if (StringUtils.startsWithIgnoreCase(errorMsg, GUEST_ONLY)){
//            json = new Json(Codes.UNAUTHEN_CODE, false, Codes.UNAUTHEN_ANON, "登录验证", "登录态验证", "system", errorMsg);
//        }else{
//            json = new Json(Codes.UNAUTHEN_CODE, false, Codes.UNAUTHZ_DESC, "登录验证", "登录态验证", "system", errorMsg);
//        }
//
//        log.error(json.toString());
//        return json;
//    }
}
