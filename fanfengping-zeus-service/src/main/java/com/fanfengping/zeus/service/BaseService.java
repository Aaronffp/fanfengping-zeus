package com.fanfengping.zeus.service;

public interface BaseService<T> {
    T add(T t);
    T update(T t);
    T delete(T t);
}
