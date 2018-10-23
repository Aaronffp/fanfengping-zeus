package com.fanfengping.zeus.repository.cicd;

import com.fanfengping.zeus.entity.cicd.Database;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository {
    @Insert("insert into `database` (env, eng, chs, valid, benchmark, type, driver, url, username, password, creater, updater, note) "
            + "value (#{env}, #{eng}, #{chs}, #{valid}, #{benchmark}, #{type}, #{driver}, #{url}, #{username}, #{password}, #{creater}, #{updater}, #{note})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    int add(Database database);

    @Update("update `database` set benchmark = 0 where eng = '${eng}' and id <> ${id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    int setBenchmarkFalse(Database database);

    @Update("update `database` set benchmark = 1 where id = '${id}'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    int setBenchmark(Database database);

    @Update("update `database` set env = '${env}', eng = '${eng}', chs = '${chs}', valid = '${valid}', " +
            "benchmark = '${benchmark}', type = '${type}', driver = '${driver}', url = '${url}', " +
            "username = '${username}', password = '${password}', creater = '${creater}', " +
            "updater = '${updater}', utime = '${utime}', note = '${note}'" +
            "where id = '${id}'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    int update(Database database);

    @Delete("delete from `database` where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    int delete(Database database);

    @Select("select * from `database` "
            + "where env like '%${env}%' and valid in ('${valid}') and eng like '%${eng}%' and chs like '%${chs}%' "
            + "order by utime desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    List<Database> findAllByConditions(@Param("env") String env, @Param("valid") String valid, @Param("eng") String eng, @Param("chs") String chs);

    @Select("select * from `database` where env = '${env}' and eng = '${eng}' ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    Database findByEnvAndEng(@Param("env") String env, @Param("eng") String eng);

    @Select("select * from `database` where benchmark = 1 and type = 'MYSQL' and env <> 'DOCKER' and url not like '%oracle%' "
            + "and eng like '%${eng}%' ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    List<Database> findAllBenchmark(@Param("eng") String eng);

    @Select("select * from `database` where eng = #{eng} and env like '%${env}%' and db_benchmark = 0 and type = 'MYSQL' and env <> 'DOCKER' and url not like '%oracle%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "chs", column = "chs"),
            @Result(property = "valid", column = "valid"),
            @Result(property = "benchmark", column = "benchmark"),
            @Result(property = "type", column = "type"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "creater", column = "creater"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "note", column = "note"),
    })
    List<Database> findAllComp(String eng, String env);
}
