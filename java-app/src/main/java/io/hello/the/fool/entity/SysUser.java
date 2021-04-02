package io.hello.the.fool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import io.hello.the.fool.common.base.entiry.AuditingEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_SYS_USER")
public class SysUser extends AuditingEntity {
    @Column(length = 64)
    private String username;
    @Column(length = 64)
    @JsonIgnore
    private String password;
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{user.invalid.email.format}")
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column
    private String salt;
    private String phone;
    @ManyToOne(optional=false, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "ou_id")
    private SysOrganizationalUnit ou; // 所属公司/组织/部门
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_SYS_USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<SysRole> roles = new HashSet<>();

    public String getRoleIdsStr() {
        if(CollectionUtils.isEmpty(roles)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(SysRole r : roles) {
            s.append(r.getId());
            s.append(",");
        }
        return s.toString();
    }
}
