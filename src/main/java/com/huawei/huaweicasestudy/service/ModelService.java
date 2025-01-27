package com.huawei.huaweicasestudy.service;

import com.huawei.huaweicasestudy.aop.annotation.Loggable;
import com.huawei.huaweicasestudy.entity.Model;
import com.huawei.huaweicasestudy.entity.ModelPartQuantity;
import com.huawei.huaweicasestudy.entity.Part;
import com.huawei.huaweicasestudy.exception.ElementNotFoundException;
import com.huawei.huaweicasestudy.mapper.ModelMapper;
import com.huawei.huaweicasestudy.payload.request.model.CreateModelRequest;
import com.huawei.huaweicasestudy.payload.request.model.UpdateModelPartQuantityRequest;
import com.huawei.huaweicasestudy.payload.response.model.CreateModelResponse;
import com.huawei.huaweicasestudy.payload.response.model.ModelDetailsResponse;
import com.huawei.huaweicasestudy.payload.response.model.ModelResponse;
import com.huawei.huaweicasestudy.payload.response.model.UpdateModelPartQuantityResponse;
import com.huawei.huaweicasestudy.repository.ModelRepository;
import com.huawei.huaweicasestudy.repository.projection.ModelItemProjection;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final PartService partService;
    private final ModelPartQuantityService modelPartQuantityService;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Loggable
    public CreateModelResponse saveModel(CreateModelRequest request) {
        Model model = new Model();
        model.setName(request.name());

        List<ModelPartQuantity> collect = request.Parts().stream().map(createPartQuantity -> {

            Part part = partService.findById(createPartQuantity.partId());

            ModelPartQuantity modelPartQuantity = new ModelPartQuantity();
            modelPartQuantity.setPart(part);
            modelPartQuantity.setModel(model);
            modelPartQuantity.setQuantity(createPartQuantity.quantity());

            return modelPartQuantity;
        }).collect(Collectors.toList());

        model.getPartQuantities().addAll(collect);
        Model saved = modelRepository.save(model);
        return modelMapper.modelToCreateModelResponse(saved);
    }

    @Transactional
    @Loggable
    public UpdateModelPartQuantityResponse updateModelPartQuantity(Long modelId, UpdateModelPartQuantityRequest createPartQuantity) {
        // Find and Validate Model and PArt
        Model model = findById(modelId);
        Part part = partService.findById(createPartQuantity.partId());

        // Find Existing ModelPartQuantity or else create new one
        ModelPartQuantity modelPartQuantity = modelPartQuantityService
                .findByModelIdAndPartIdOptional(modelId, part.getId())
                .orElseGet(() -> createModelPArtQuantity(part, model));

        modelPartQuantity.setQuantity(createPartQuantity.quantity());
        // Save To DB
        model.getPartQuantities().add(modelPartQuantity);
        modelRepository.save(model);

        return modelMapper.toAddPartToModelResponse(modelPartQuantity);
    }

    @Loggable
    public Page<ModelResponse> findAll(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber == null ? 0 : pageNumber, 100, Sort.by("name").ascending());
        Page<ModelItemProjection> parts = modelRepository.findBy(pageable);
        return parts.map(modelMapper::modelItemProjectionToModelResponse);
    }

    @Loggable
    public ModelDetailsResponse findModelDetails(Long modelId) {
        Model model = modelRepository.findByIdWithParts(modelId)
                .orElseThrow(() -> new ElementNotFoundException(Model.class, modelId));
        return modelMapper.toModelDetailsResponse(model);
    }

    @Loggable
    public Model findById(Long modelId) {
        return modelRepository.findById(modelId).orElseThrow(() -> new ElementNotFoundException(Model.class, modelId));
    }

    private ModelPartQuantity createModelPArtQuantity(Part part, Model model) {
        ModelPartQuantity modelPartQuantity = new ModelPartQuantity();
        modelPartQuantity.setPart(part);
        modelPartQuantity.setModel(model);
        return modelPartQuantity;
    }
}