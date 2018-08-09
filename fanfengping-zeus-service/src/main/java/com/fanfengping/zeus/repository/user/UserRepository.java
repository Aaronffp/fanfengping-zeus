package com.fanfengping.zeus.repository.user;

import com.fanfengping.zeus.entity.user.ZeusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ZeusUser, String> {
    /**
     * 通过账号、密钥获取用户信息
     * @param account
     * @param passwd
     * @return
     */
    ZeusUser findByAccountEqualsAndPasswdEquals(String account, String passwd);
}
