package com.huawei.huaweicasestudy.config;

import com.huawei.huaweicasestudy.entity.OperationType;
import com.huawei.huaweicasestudy.entity.Log;
import com.huawei.huaweicasestudy.entity.Model;
import com.huawei.huaweicasestudy.payload.request.model.CreateModelRequest;
import com.huawei.huaweicasestudy.payload.request.model.UpdateModelPartQuantityRequest;
import com.huawei.huaweicasestudy.payload.request.part.CreatePartRequest;
import com.huawei.huaweicasestudy.payload.response.model.CreateModelResponse;
import com.huawei.huaweicasestudy.payload.response.part.CreatePartResponse;
import com.huawei.huaweicasestudy.repository.LogRepository;
import com.huawei.huaweicasestudy.repository.ModelRepository;
import com.huawei.huaweicasestudy.service.ModelService;
import com.huawei.huaweicasestudy.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private final PartService partService;
    private final ModelService modelService;

    private final LogRepository logRepository;

    @Override
    public void run(String... args) {

        //CREATE LOGS
        for (int i = 0; i < 10; i++) {
            logRepository.save(new Log(OperationType.CREATE, "savePartTest"));
        }
        for (int i = 0; i < 10; i++) {
            logRepository.save(new Log(OperationType.UPDATE, "updatePartTest"));
        }
        for (int i = 0; i < 10; i++) {
            logRepository.save(new Log(OperationType.OTHER, "someMethodTest"));
        }

        // CREATE 2 PART ( test1: 1  | test2: 2 )
        CreatePartResponse test1 = partService.savePart(new CreatePartRequest("part1"));
        CreatePartResponse test2 = partService.savePart(new CreatePartRequest("part2"));
        System.out.println(test1.id());
        System.out.println(test2.id());

        //CREATE MODEL (model1: 1 | part1 (50) | part2 (100))
        List<UpdateModelPartQuantityRequest> listPart = new ArrayList<>();
        listPart.add(new UpdateModelPartQuantityRequest(test1.id(), 50));
        listPart.add(new UpdateModelPartQuantityRequest(test2.id(), 100));
        CreateModelRequest model1 = new CreateModelRequest("model1", listPart);

        //CREATE MODEL (model2: 2 | part1 (5) | part2 (10)  )
        List<UpdateModelPartQuantityRequest> listPart2 = new ArrayList<>();
        listPart2.add(new UpdateModelPartQuantityRequest(test1.id(), 5));
        listPart2.add(new UpdateModelPartQuantityRequest(test2.id(), 10));
        CreateModelRequest model2 = new CreateModelRequest("model2", listPart2);

        CreateModelResponse response = modelService.saveModel(model1);
        CreateModelResponse response1 = modelService.saveModel(model2);

    }
}
