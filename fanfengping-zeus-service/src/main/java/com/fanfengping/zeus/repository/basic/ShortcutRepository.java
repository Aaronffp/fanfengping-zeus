package com.fanfengping.zeus.repository.basic;

import com.fanfengping.zeus.entity.basic.Shortcut;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortcutRepository {
    @Insert("insert into shortcut "
            + "value (#{dis}, #{disorder}, #{name}, #{icon}, #{url}, #{manager}, #{mobile}, #{note}, #{operator})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dis", column = "dis"),
            @Result(property = "disorder", column = "disorder"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "url", column = "url"),
            @Result(property = "manager", column = "manager"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "note", column = "note"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    Integer add(Shortcut shortcut);

    @Update("update shortcut set dis = '${dis}', disorder = '${disorder}', name = '${name}', icon = '${icon}', " +
            "       url = '${url}', manager = '${manager}', mobile = '${mobile}', note = '${note}', " +
            "       operator = '${operator}', utime = NOW() " +
            " where id = '${id}'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dis", column = "dis"),
            @Result(property = "disorder", column = "disorder"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "url", column = "url"),
            @Result(property = "manager", column = "manager"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "note", column = "note"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    Integer update(Shortcut shortcut);

    @Delete("delete from shortcut where id = '${id}' ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dis", column = "dis"),
            @Result(property = "disorder", column = "disorder"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "url", column = "url"),
            @Result(property = "manager", column = "manager"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "note", column = "note"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    Integer delete(Shortcut shortcut);

    @Select("select * from shortcut order by dis desc, disorder ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dis", column = "dis"),
            @Result(property = "disorder", column = "disorder"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "url", column = "url"),
            @Result(property = "manager", column = "manager"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "note", column = "note"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    List<Shortcut> findAll();
}
