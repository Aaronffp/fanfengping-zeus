package com.fanfengping.zeus.repository.user;

import com.fanfengping.zeus.entity.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    @Insert("insert into user (uuid, account, passwd, salt, name, mobile, email, state, operator) "
            + "value(REPLACE(UUID(), '-', ''), '${account}', '${passwd}', '${salt}', '${name}', '${mobile}', '${email}', ${state}, '${operator}') ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "account", column = "account"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    int add(User user);

    @Update("update user set account = '${account}', passwd = '${passwd}', salt = '${salt}', " +
            "       name = '${name}', mobile = '${mobile}', email = '${email}', state = '${state}', " +
            "       operator = '${operator}', utime = NOW() "
            + "where id = '${id}' and uuid = '${uuid}' ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "account", column = "account"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    int update(User user);

    @Delete("delete from user where id = '${id}' ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "account", column = "account"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    int delete(User user);

    @Select("select id, uuid, account, passwd, salt, name, mobile, email, state, operator, ctime, utime "
            + "from user "
            + "where account like '%${account}%' and name like '%${name}%' " +
            "    and mobile like '%${mobile}%' and email like '%${email}%' " +
            "  order by state desc, utime desc ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "account", column = "account"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    List<User> findAllByConditions(@Param("account") String account,
                                   @Param("name") String name,
                                   @Param("mobile") String mobile,
                                   @Param("email") String email);

    @Select("select id, uuid, account, passwd, salt, name, mobile, email, state, operator, ctime, utime "
            + "from user "
            + "where account = '${account}' ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "account", column = "account"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    User findByAccount(@Param("account") String account);

    @Select("select id, uuid, account, passwd, salt, name, mobile, email, state, operator, ctime, utime "
            + "from user "
            + "where account = '${account}' and passwd = '${passwd}' order by state desc, utime desc ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "account", column = "account"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    User findByAccountAndPasswd(String account, String passwd);
}
