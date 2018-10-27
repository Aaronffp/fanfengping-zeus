package com.fanfengping.zeus.service;

import com.fanfengping.zeus.util.ResponseJson;

public interface BaseService<T> {
    ResponseJson add(T t);
    ResponseJson update(T t);
    ResponseJson delete(T t);
}
