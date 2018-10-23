package com.fanfengping.zeus.repository.cicd;

import com.fanfengping.zeus.entity.cicd.DataDictionary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataDictionaryRepository {
    @Insert("insert into `data_dictionary` (sn, databaseId, env, eng, url, username, password, " +
            "       table_schema, table_name, table_comment, engine, table_collation, " +
            "       column_name, column_comment, column_key, column_type, nullable, " +
            "       column_default, character_set_name, collation_name) "
            + "value (#{sn}, #{databaseId}, #{env}, #{eng}, #{url}, #{username}, #{password}, " +
            "         #{tableSchema}, #{tableName}, #{tableComment}, #{engine}, #{tableCollation}, " +
            "         #{columnName}, #{columnComment}, #{columnKey}, #{columnType}, #{nullable}, #{columnDefault}, " +
            "         #{characterSetName}, #{collationName})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sn", column = "sn"),
            @Result(property = "databaseId", column = "databaseId"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "tableSchema", column = "table_schema"),
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "tableComment", column = "table_comment"),
            @Result(property = "engine", column = "engine"),
            @Result(property = "tableCollation", column = "table_collation"),
            @Result(property = "columnName", column = "column_name"),
            @Result(property = "columnComment", column = "column_comment"),
            @Result(property = "columnKey", column = "column_key"),
            @Result(property = "columnType", column = "column_type"),
            @Result(property = "nullable", column = "nullable"),
            @Result(property = "columnDefault", column = "column_default"),
            @Result(property = "characterSetName", column = "character_set_name"),
            @Result(property = "collationName", column = "collation_name"),
            @Result(property = "ctime", column = "ctime"),
    })
    int add(DataDictionary dataDictionary);

    @Select("SELECT * FROM data_dictionary " +
            "WHERE env LIKE '%${env}%' AND eng LIKE '%${eng}%'  AND url like '%${url}%' AND table_name like '%${tableName}%' " +
            "  AND column_name like '%${columnName}%' AND ctime > DATE_ADD(NOW(),INTERVAL -30 MINUTE) " +
            "ORDER BY id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "sn", column = "sn"),
            @Result(property = "databaseId", column = "databaseId"),
            @Result(property = "env", column = "env"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "url", column = "url"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "tableSchema", column = "table_schema"),
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "tableComment", column = "table_comment"),
            @Result(property = "engine", column = "engine"),
            @Result(property = "tableCollation", column = "table_collation"),
            @Result(property = "columnName", column = "column_name"),
            @Result(property = "columnComment", column = "column_comment"),
            @Result(property = "columnKey", column = "column_key"),
            @Result(property = "columnType", column = "column_type"),
            @Result(property = "nullable", column = "nullable"),
            @Result(property = "columnDefault", column = "column_default"),
            @Result(property = "characterSetName", column = "character_set_name"),
            @Result(property = "collationName", column = "collation_name"),
            @Result(property = "ctime", column = "ctime"),
    })
    List<DataDictionary> findAllByConditions(@Param("env") String env,
                                             @Param("eng") String eng,
                                             @Param("url") String url,
                                             @Param("tableName") String tableName,
                                             @Param("columnName") String columnName);
}

