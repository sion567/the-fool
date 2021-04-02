package io.hello.the.fool.entity;

import io.hello.the.fool.common.base.entiry.AuditingEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_SYS_ROLE")
public class SysRole extends AuditingEntity {
    private String roleName;
    private String roleKey;
    private int roleSort;
    private Integer roleLevel;
    private String description;
    private String menuItems;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_SYS_ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<SysResource> roles = new HashSet<>();
}
