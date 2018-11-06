package com.fanfengping.zeus.controller;

import com.fanfengping.zeus.util.ResponseJson;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<T> {
    ResponseJson add(@RequestBody T t);
    ResponseJson update(@RequestBody T t);
    ResponseJson delete(@RequestBody T t);
}
