package com.fanfengping.zeus.repository.cicd;

import com.fanfengping.zeus.entity.cicd.BuildHistory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildHistoryRepository {
    @Insert("insert into build_history(eng, tag, publish, git_url, git_branch, addr, link, operator)"
            + "values (#{eng}, #{tag}, #{publish}, #{gitUrl}, #{gitBranch}, #{addr}, #{link}, #{operator})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "gitUrl", column = "git_url"),
            @Result(property = "gitBranch", column = "git_branch"),
            @Result(property = "addr", column = "addr"),
            @Result(property = "link", column = "link"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
    })
    Integer add(BuildHistory BuildHistory);

    @Update("update build_history set eng = '${eng}', tag = '${tag}', `publish` = '${publish}', " +
            "git_url = '${gitUrl}', git_branch = '${gitBranch}', addr = '${addr}', link = '${link}', " +
            "operator = '${operator}', ctime = NOW() " +
            "where id = ${id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "gitUrl", column = "git_url"),
            @Result(property = "gitBranch", column = "git_branch"),
            @Result(property = "addr", column = "addr"),
            @Result(property = "link", column = "link"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
    })
    int update(BuildHistory BuildHistory);

    @Delete("delete from build_history where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "gitUrl", column = "git_url"),
            @Result(property = "gitBranch", column = "git_branch"),
            @Result(property = "addr", column = "addr"),
            @Result(property = "link", column = "link"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
    })
    int delete(BuildHistory BuildHistory);

    @Select("select * from build_history "
            + "where publish in (${publish}) and addr like '%${addr}%' " +
            "    and eng like '%${eng}%' and git_url like '%${gitUrl}%' " +
            "    and git_branch like '%${gitBranch}%' and operator like '%${operator}%' "
            + "order by ctime desc limit 50")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "eng", column = "eng"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "publish", column = "publish"),
            @Result(property = "gitUrl", column = "git_url"),
            @Result(property = "gitBranch", column = "git_branch"),
            @Result(property = "addr", column = "addr"),
            @Result(property = "link", column = "link"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "ctime", column = "ctime"),
    })
    List<BuildHistory> findAllByConditions(@Param("eng") String eng,
                                           @Param("publish") String publish,
                                           @Param("addr") String addr,
                                           @Param("gitUrl") String gitUrl,
                                           @Param("gitBranch") String gitBranch,
                                           @Param("operator") String operator);
}
