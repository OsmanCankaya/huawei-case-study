package com.huawei.huaweicasestudy.repository;

import com.huawei.huaweicasestudy.entity.Model;
import com.huawei.huaweicasestudy.repository.projection.ModelItemProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Page<ModelItemProjection> findBy(Pageable pageable);

    @Query("SELECT m FROM Model m " +
            "JOIN FETCH m.partQuantities pq " +
            "JOIN FETCH pq.part " +
            "WHERE m.id = :modelId AND m.deleted = false")
    Optional<Model> findByIdWithParts(@Param("modelId") Long modelId);
}
