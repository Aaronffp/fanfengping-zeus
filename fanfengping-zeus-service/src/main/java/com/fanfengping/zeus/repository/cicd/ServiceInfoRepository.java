package com.fanfengping.zeus.repository.cicd;

import com.fanfengping.zeus.entity.cicd.ServiceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceInfoRepository {
    @Insert("insert into service_info (env, eng, chs, url, account, password, note, updater) "
            + "value (#{env}, #{eng}, #{chs}, #{url}, #{account}, #{password}, #{note}, #{updater})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "url", column = "url"),
            @Result(property = "account", column = "account"),
            @Result(property = "password", column = "password"),
            @Result(property = "note", column = "note"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
    })
    Integer add(ServiceInfo serviceInfo);

    @Update("update service_info set env = '${env}', eng = '${eng}', chs = '${chs}', url = '${url}', " +
            "       account = '${account}', password = '${password}', note = '${note}, " +
            "       updater = '${updater}', utime = NOW() " +
            "where id = '${id}'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "url", column = "url"),
            @Result(property = "account", column = "account"),
            @Result(property = "password", column = "password"),
            @Result(property = "note", column = "note"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
    })
    int update(ServiceInfo serviceInfo);

    @Delete("delete from service_info where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "url", column = "url"),
            @Result(property = "account", column = "account"),
            @Result(property = "password", column = "password"),
            @Result(property = "note", column = "note"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
    })
    int delete(ServiceInfo serviceInfo);

    @Select("select * from service_info "
            + "where env like '%${env}%' and eng like '%${eng}%' and chs like '%${chs}%' " +
            "        and updater like '%${updater}%' "
            + "order by utime desc ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "url", column = "url"),
            @Result(property = "account", column = "account"),
            @Result(property = "password", column = "password"),
            @Result(property = "note", column = "note"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
    })
    List<ServiceInfo> findAllByConditions(@Param("env") String env, @Param("eng") String eng, @Param("chs") String schs, @Param("updater") String updater);
}
