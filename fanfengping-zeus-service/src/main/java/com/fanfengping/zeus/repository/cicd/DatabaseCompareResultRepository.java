package com.fanfengping.zeus.repository.cicd;

import com.fanfengping.zeus.entity.cicd.DatabaseCompareResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseCompareResultRepository {
    @Insert("insert into database_compare (flag, status, eng, benchmark_id, benchmark_env, benchmark_url, target_id, target_env, target_url, info, note) "
            + "values (#{flag}, #{status}, #{eng}, #{benchmarkId}, #{benchmarkEnv}, #{benchmarkUrl}, #{targetId}, #{targetEnv}, #{targetUrl}, #{info}, #{note})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "status", column = "status"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "benchmarkId", column = "benchmark_id"),
            @Result(property = "benchmarkEnv", column = "benchmark_env"),
            @Result(property = "benchmarkUrl", column = "benchmark_url"),
            @Result(property = "targetId", column = "target_id"),
            @Result(property = "targetEnv", column = "target_env"),
            @Result(property = "targetUrl", column = "target_url"),
            @Result(property = "info", column = "info"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    Integer add(DatabaseCompareResult databaseCompareResult);

    @Select(" select id, flag, status, eng, benchmark_id, benchmark_env, benchmark_url, target_id, " +
            "        target_env, target_url, info, note, ctime "
            + " from database_compare "
            + "where flag = -1 and ctime > DATE_ADD(NOW(),INTERVAL -30 MINUTE) and "
            + "      eng like '%${eng}%' and target_env like '%${env}%' "
            + "order by env, id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "status", column = "status"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "benchmarkId", column = "benchmark_id"),
            @Result(property = "benchmarkEnv", column = "benchmark_env"),
            @Result(property = "benchmarkUrl", column = "benchmark_url"),
            @Result(property = "targetId", column = "target_id"),
            @Result(property = "targetEnv", column = "target_env"),
            @Result(property = "targetUrl", column = "target_url"),
            @Result(property = "info", column = "info"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    List<DatabaseCompareResult> findAllByConditions(@Param("eng") String eng, @Param("env") String env);

    @Select(" select id, flag, status, eng, benchmark_id, benchmark_env, benchmark_url, target_id, " +
            "        target_env, target_url, info, note, ctime "
            + " from database_compare "
            + "where flag = -1 and ctime > DATE_ADD(NOW(),INTERVAL -30 MINUTE) and "
            + "      eng like '%${eng}%' and target_env like '%${env}%' "
            + "order by env, id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "status", column = "status"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "benchmarkId", column = "benchmark_id"),
            @Result(property = "benchmarkEnv", column = "benchmark_env"),
            @Result(property = "benchmarkUrl", column = "benchmark_url"),
            @Result(property = "targetId", column = "target_id"),
            @Result(property = "targetEnv", column = "target_env"),
            @Result(property = "targetUrl", column = "target_url"),
            @Result(property = "info", column = "info"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    DatabaseCompareResult findByEngAndTargerEnv(@Param("eng") String service, @Param("env") String env);

    @Select("select * from database_compare "
            + "where ctime > DATE_ADD(NOW(),INTERVAL -30 MINUTE) "
            + "and eng like '%${eng}%' and target_env like '%${env}%' limit 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "status", column = "status"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "benchmarkId", column = "benchmark_id"),
            @Result(property = "benchmarkEnv", column = "benchmark_env"),
            @Result(property = "benchmarkUrl", column = "benchmark_url"),
            @Result(property = "targetId", column = "target_id"),
            @Result(property = "targetEnv", column = "target_env"),
            @Result(property = "targetUrl", column = "target_url"),
            @Result(property = "info", column = "info"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    List<DatabaseCompareResult> exist(@Param("eng") String eng, @Param("env") String env);

    @Delete("delete from database_compare where ctime > DATE_ADD(NOW(),INTERVAL -30 MINUTE) "
            + "and eng like '%${eng}%' and target_env like '%${env}%' ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "status", column = "status"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "benchmarkId", column = "benchmark_id"),
            @Result(property = "benchmarkEnv", column = "benchmark_env"),
            @Result(property = "benchmarkUrl", column = "benchmark_url"),
            @Result(property = "targetId", column = "target_id"),
            @Result(property = "targetEnv", column = "target_env"),
            @Result(property = "targetUrl", column = "target_url"),
            @Result(property = "info", column = "info"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    void delete(@Param("eng") String eng, @Param("env") String env);
}
