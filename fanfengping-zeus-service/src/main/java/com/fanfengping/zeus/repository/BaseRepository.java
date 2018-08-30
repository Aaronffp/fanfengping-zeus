package com.fanfengping.zeus.repository;

public interface BaseRepository<T> {
    int add(T t);
    int update(T t);
    int delete(T t);
}
