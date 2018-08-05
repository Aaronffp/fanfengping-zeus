package com.fanfengping.zeus.repository;

import com.fanfengping.zeus.entity.ZeusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ZeusUser, String> {
    ZeusUser findOneByAccountAndPasswd(String account, String passwd);
}
