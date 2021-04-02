package io.hello.the.fool.common.base.dao;

import io.hello.the.fool.common.base.entiry.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable>
        extends JpaRepository<T,ID> , JpaSpecificationExecutor<T> {

    @Modifying
    @Query("update #{#entityName} o set o.available = 1 where o.id = :id")
    void remove(@Param("id") ID id);
}