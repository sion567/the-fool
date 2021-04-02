package io.hello.the.fool.service;

import io.hello.the.fool.entity.SysUser;
import io.hello.the.fool.repository.SysUserRepository;
import io.hello.the.fool.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.hasText;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (!hasText(username)) {
            throw new IllegalArgumentException("用户名为空");
        }

        SysUser user = sysUserRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new UserDetails(user);
    }
}