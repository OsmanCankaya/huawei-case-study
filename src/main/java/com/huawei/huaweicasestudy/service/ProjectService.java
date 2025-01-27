package com.huawei.huaweicasestudy.service;

import com.huawei.huaweicasestudy.aop.annotation.Loggable;
import com.huawei.huaweicasestudy.entity.PlanningType;
import com.huawei.huaweicasestudy.entity.Project;
import com.huawei.huaweicasestudy.exception.ProjectInfosNotFoundException;
import com.huawei.huaweicasestudy.mapper.ProjectMapper;
import com.huawei.huaweicasestudy.payload.request.project.CreateFixedProjectRequest;
import com.huawei.huaweicasestudy.payload.request.project.CreateMonthlyProjectRequest;
import com.huawei.huaweicasestudy.payload.request.project.CreateWeeklyProjectRequest;
import com.huawei.huaweicasestudy.payload.response.project.CreateProjectResponse;
import com.huawei.huaweicasestudy.payload.response.project.ProjectPartUsageResponse;
import com.huawei.huaweicasestudy.repository.ProjectRepository;
import com.huawei.huaweicasestudy.repository.projection.ProjectCalculationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ModelService modelService;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Loggable
    public CreateProjectResponse saveProject(CreateFixedProjectRequest createModelRequest) {
        Project project = projectMapper.mapFrom(createModelRequest);
        project.setPlanningType(PlanningType.FIXED);
        Project savedProject = projectRepository.save(project);
        return projectMapper.projectToCreateProjectResponse(savedProject);
    }

    @Loggable
    public CreateProjectResponse saveProject(CreateMonthlyProjectRequest createModelRequest) {
        Project project = projectMapper.mapFrom(createModelRequest);
        project.setPlanningType(PlanningType.MONTHLY);
        Project savedProject = projectRepository.save(project);
        return projectMapper.projectToCreateProjectResponse(savedProject);
    }

    @Loggable
    public CreateProjectResponse saveProject(CreateWeeklyProjectRequest createModelRequest) {
        Project project = projectMapper.mapFrom(createModelRequest);
        project.setPlanningType(PlanningType.WEEKLY);
        Project savedProject = projectRepository.save(project);
        return projectMapper.projectToCreateProjectResponse(savedProject);
    }

    @Loggable
    public ProjectPartUsageResponse calculatePartUsageInProject(Long projectId, int monthToCalculate) {
        List<ProjectCalculationInfo> projectCalculationInfos = projectRepository.findProjectModelPartsByProjectAndMonth(projectId, monthToCalculate);
        if (projectCalculationInfos.isEmpty())
            throw new ProjectInfosNotFoundException();
        //Map for partId and quantity
        HashMap<Long, Integer> partUsage = new HashMap<>();

        for (ProjectCalculationInfo info : projectCalculationInfos) {
            int quantityCount = info.quantity() * (info.goal() * info.percentage() / 100);
            partUsage.merge(info.partId(), quantityCount, Integer::sum);
        }

        return new ProjectPartUsageResponse(
                projectId,
                monthToCalculate,
                partUsage);
    }
}
