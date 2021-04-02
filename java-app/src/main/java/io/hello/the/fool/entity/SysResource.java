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
@Table(name = "T_SYS_RESOURCE")
public class SysResource extends AuditingEntity {
    private String name; //资源名称
    private ResourceType type = ResourceType.menu; //资源类型
    private String url; //资源路径
    private String permission; //权限字符串
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "parent_id")
    private SysResource parent;
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<SysResource> children = new HashSet<>();
    private String parentIds; //父编号列表

    public static enum ResourceType {
        menu("菜单"), button("按钮");
        private final String info;
        private ResourceType(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
    }

    public boolean isRootNode() {
        return parent.getId() == 1;
    }
    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
}
