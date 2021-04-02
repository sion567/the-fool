package io.hello.the.fool.entity;

import io.hello.the.fool.common.base.entiry.AuditingEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_SYS_AUTHORIZATION")
public class SysAuthorization extends AuditingEntity {
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private SysUser user;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "ou_id")
    private SysOrganizationalUnit ou;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private SysRole role;
}
