package com.fanfengping.zeus.repository.user;

import com.fanfengping.zeus.entity.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    @Insert("insert into user (uuid, username, password, salt, name, mobile, email, state, operator) "
            + "value(REPLACE(UUID(), '-', ''), '${username}', '${password}', '${salt}', '${name}', '${mobile}', '${email}', ${state}, '${operator}') ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
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

    @Update("update user set username = '${username}', password = '${password}', salt = '${salt}', " +
            "       name = '${name}', mobile = '${mobile}', email = '${email}', state = '${state}', " +
            "       operator = '${operator}', utime = NOW() "
            + "where id = '${id}' and uuid = '${uuid}' ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
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
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
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

    @Select("select id, uuid, username, password, salt, name, mobile, email, state, operator, ctime, utime "
            + "from user "
            + "where username like '%${username}%' and name like '%${name}%' " +
            "    and mobile like '%${mobile}%' and email like '%${email}%' " +
            "  order by state desc, utime desc ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    List<User> findAllByConditions(@Param("username") String username,
                                   @Param("name") String name,
                                   @Param("mobile") String mobile,
                                   @Param("email") String email);

    @Select("select id, uuid, username, password, salt, name, mobile, email, state, operator, ctime, utime "
            + "from user "
            + "where username = '${username}' ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    User findByUsername(@Param("username") String username);

    @Select("select id, uuid, username, password, salt, name, mobile, email, state, operator, ctime, utime "
            + "from user "
            + "where username = '${username}' and password = '${password}' order by state desc, utime desc ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
    })
    User findByUsernameAndPassword(String username, String password);
}
