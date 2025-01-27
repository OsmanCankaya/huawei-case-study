package com.huawei.huaweicasestudy.repository;

import com.huawei.huaweicasestudy.entity.ModelPartQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelPartQuantityRepository extends JpaRepository<ModelPartQuantity, Long> {

    Optional<ModelPartQuantity> findByModelIdAndPartId(Long modelId, Long partId);
}
