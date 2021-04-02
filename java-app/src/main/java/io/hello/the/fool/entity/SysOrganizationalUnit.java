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
@Table(name = "T_SYS_ORGAIZATIONAL_UNIT")
public class SysOrganizationalUnit extends AuditingEntity {
    private String name;
    @OneToOne(cascade= CascadeType.ALL)//D1是关系的维护端，当删除 d1，会级联删除 d2
    @JoinColumn(name = "leader_user_id", referencedColumnName = "id")
    private SysUser leader;
    private String phone;
    private String descritpion;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "parent_id")
    private SysOrganizationalUnit parent;
    private String parentIds; //父编号列表，如1/2/
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<SysOrganizationalUnit> children = new HashSet<>();

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
    public boolean isRootNode() {
        return parent.getId() == 1;
    }
}
