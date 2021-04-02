package io.hello.the.fool.common.base.entiry;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;
import javax.persistence.EntityListeners;

import java.util.Calendar;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditingEntity extends BaseEntity {
    private static final long serialVersionUID = -7846656092107790576L;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_date")
    private Calendar createdDate;
    @CreatedBy
    @Column(name = "created_user")
    private Long createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "modified_date")
    private Calendar lastModifiedDate;
    @LastModifiedBy
    @Column(name = "modified_user")
    private Long lastModifyBy;
}
