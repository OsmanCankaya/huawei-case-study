package com.huawei.huaweicasestudy.repository;

import com.huawei.huaweicasestudy.entity.Part;
import com.huawei.huaweicasestudy.repository.projection.PartItemProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PartRepository extends JpaRepository<Part, Long> {
    Page<PartItemProjection> findBy(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Part P SET P.deleted = true WHERE P.id = :partId AND P.modelPartQuantities IS EMPTY")
    Integer markAsDeletedByIdIfModelPartQuantitiesIsEmpty(Long partId);
}
