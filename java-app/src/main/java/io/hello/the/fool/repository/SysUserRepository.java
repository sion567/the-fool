package io.hello.the.fool.repository;

import io.hello.the.fool.common.base.dao.BaseRepository;
import io.hello.the.fool.entity.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("sysUserDao")
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    @Query("SELECT u FROM SysUser u WHERE u.username = :username")
    SysUser getUserByUsername(@Param("username") String username);
}
