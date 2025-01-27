package com.huawei.huaweicasestudy.service;

import com.huawei.huaweicasestudy.aop.annotation.Loggable;
import com.huawei.huaweicasestudy.entity.Part;
import com.huawei.huaweicasestudy.exception.ElementNotFoundException;
import com.huawei.huaweicasestudy.exception.ItemIsNotDeleteableException;
import com.huawei.huaweicasestudy.mapper.PartMapper;
import com.huawei.huaweicasestudy.payload.request.part.CreatePartRequest;
import com.huawei.huaweicasestudy.payload.request.part.UpdatePartRequest;
import com.huawei.huaweicasestudy.payload.response.part.CreatePartResponse;
import com.huawei.huaweicasestudy.payload.response.part.PartResponse;
import com.huawei.huaweicasestudy.payload.response.part.UpdatePartResponse;
import com.huawei.huaweicasestudy.repository.PartRepository;
import com.huawei.huaweicasestudy.repository.projection.PartItemProjection;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository repository;
    private final PartMapper mapper;

    @Loggable
    public CreatePartResponse savePart(CreatePartRequest partRequest) {
        Part part = mapper.mapFromCreatePartRequest(partRequest);
        Part savedPart = repository.save(part);
        CreatePartResponse createPartResponse = mapper.mapToCreatePartResponse(savedPart);
        return createPartResponse;
    }

    @Transactional
    @Loggable
    public UpdatePartResponse updatePart(Long id, UpdatePartRequest partRequest) {
        Part part = repository.findById(id).orElseThrow(() -> new ElementNotFoundException(Part.class, id));
        part.setName(partRequest.name());
        Part savedPart = repository.save(part);
        return mapper.mapToUpdatePartResponse(savedPart);
    }

    @Loggable
    public Page<PartResponse> findAll(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber == null ? 0 : pageNumber, 100, Sort.by("name").ascending());
        Page<PartItemProjection> parts = repository.findBy(pageable);
        return parts.map(mapper::mapToPartResponse);
    }

    @Loggable
    public void delete(Long partId) {
        int rowsAffected = repository.markAsDeletedByIdIfModelPartQuantitiesIsEmpty(partId);
        if (rowsAffected == 0) {
            throw new ItemIsNotDeleteableException(Part.class);
        }
    }

    @Loggable
    public Part findById(Long partId) {
        return repository.findById(partId).orElseThrow(() -> new ElementNotFoundException(Part.class, partId));
    }
}