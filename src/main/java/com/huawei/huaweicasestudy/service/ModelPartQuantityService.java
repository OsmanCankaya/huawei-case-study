package com.huawei.huaweicasestudy.service;

import com.huawei.huaweicasestudy.entity.ModelPartQuantity;
import com.huawei.huaweicasestudy.repository.ModelPartQuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelPartQuantityService {
    private final ModelPartQuantityRepository repository;

    public Optional<ModelPartQuantity> findByModelIdAndPartIdOptional(Long modelID, Long partID) {
        return repository.findByModelIdAndPartId(modelID, partID);
    }
}
