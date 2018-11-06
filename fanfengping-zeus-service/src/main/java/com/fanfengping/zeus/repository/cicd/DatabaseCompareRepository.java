package com.fanfengping.zeus.repository.cicd;

import com.fanfengping.zeus.entity.cicd.DatabaseCompare;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseCompareRepository {
    @Insert("insert into database_compare (flag, status, eng, benchmark_id, benchmark_env, benchmark_url, target_id, target_env, target_url, table_name, info, benchmark_detail, target_detail, note) "
            + "values (#{flag}, #{status}, #{eng}, #{benchmarkId}, #{benchmarkEnv}, #{benchmarkUrl}, #{targetId}, #{targetEnv}, #{targetUrl}, #{tableName}, #{info}, #{note})")
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
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "benchmarkDetail", column = "benchmark_detail"),
            @Result(property = "targetDetail", column = "target_detail"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    Integer add(DatabaseCompare databaseCompare);

    @Select(" select * from database_compare "
            + "where status = -1 and ctime > DATE_ADD(NOW(),INTERVAL -10 MINUTE) and "
            + "      eng like '%${eng}%' and table_name like '%${tableName}%' and benchmark_env like '%${bEnv}%' and target_env like '%${tEnv}%' "
            + "order by id")
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
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "info", column = "info"),
            @Result(property = "benchmarkDetail", column = "benchmark_detail"),
            @Result(property = "targetDetail", column = "target_detail"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    List<DatabaseCompare> findAllByConditions(@Param("eng") String eng,
                                              @Param("bEnv") String bEnv,
                                              @Param("tEnv") String tEnv,
                                              @Param("tableName") String tableName);

    @Select("select * from database_compare "
            + "where ctime > DATE_ADD(NOW(),INTERVAL -10 MINUTE) "
            + "and eng like '%${eng}%' and benchmark_env like '%${bEnv}%' and target_env like '%${tEnv}%' limit 1")
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
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "info", column = "info"),
            @Result(property = "benchmarkDetail", column = "benchmark_detail"),
            @Result(property = "targetDetail", column = "target_detail"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    List<DatabaseCompare> exist(@Param("eng") String eng, @Param("bEnv") String bEnv, @Param("tEnv") String tEnv);

    @Delete("delete from database_compare where ctime > DATE_ADD(NOW(),INTERVAL -10 MINUTE) "
            + "and eng like '%${eng}%' and benchmark_env like '%${bEnv}%' and target_env like '%${tEnv}%' ")
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
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "info", column = "info"),
            @Result(property = "benchmarkDetail", column = "benchmark_detail"),
            @Result(property = "targetDetail", column = "target_detail"),
            @Result(property = "note", column = "note"),
            @Result(property = "ctime", column = "ctime"),
    })
    void delete(@Param("eng") String eng, @Param("bEnv") String bEnv, @Param("tEnv") String tEnv);
}
