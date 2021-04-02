package io.hello.the.fool.service;

import io.hello.the.fool.common.base.entiry.BaseEntity;
import io.hello.the.fool.vo.UserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Set;

@Log4j2
@Configuration
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        UserDetails customUserDetails = (UserDetails) authentication.getPrincipal();
        BaseEntity abstractEntity = (BaseEntity) targetDomainObject;
        log.debug("User {} trying to access {}-{} with permission {}",
                customUserDetails.getUsername(),
                abstractEntity.getClass().getSimpleName(),
                abstractEntity.getId(),
                permission.toString());
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        UserDetails customUserDetails = (UserDetails) authentication.getPrincipal();
        log.debug("User {} trying to access {}-{} with permission {}",
                customUserDetails.getUsername(),
                targetType,
                targetId,
                permission.toString());
        return false;
    }

//    private boolean hasPermission(Set<SysPermission> permissions, SysPermission permission) {
//        return permissions
//                .stream()
//                .filter(setPermission -> setPermission.equals(permission))
//                .findFirst().isPresent();
//    }
}
