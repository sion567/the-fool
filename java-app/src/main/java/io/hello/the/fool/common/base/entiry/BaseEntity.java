package io.hello.the.fool.common.base.entiry;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Data
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
    @TableGenerator(name = "table-generator",
            table = "t_ids",
            pkColumnName = "seq_id",
            valueColumnName = "seq_value")
    private Long id;

    @Column(name = "available_state", columnDefinition="INT(1) default 0")
    private boolean available ;

    @Version
    protected Integer version;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
