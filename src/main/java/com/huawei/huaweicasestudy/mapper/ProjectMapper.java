package com.huawei.huaweicasestudy.mapper;

import com.huawei.huaweicasestudy.entity.Project;
import com.huawei.huaweicasestudy.payload.request.project.CreateFixedProjectRequest;
import com.huawei.huaweicasestudy.payload.request.project.CreateMonthlyProjectRequest;
import com.huawei.huaweicasestudy.payload.request.project.CreateWeeklyProjectRequest;
import com.huawei.huaweicasestudy.payload.response.project.CreateProjectResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {
    CreateProjectResponse projectToCreateProjectResponse(Project savedProject);

    Project mapFrom(CreateFixedProjectRequest createFixedProjectRequest);

    Project mapFrom(CreateMonthlyProjectRequest createMonthlyProjectRequest);

    Project mapFrom(CreateWeeklyProjectRequest createWeeklyProjectRequest);

    @AfterMapping
    default void setRelationships(@MappingTarget Project project) {
        if (project.getProjectGoals() != null) {
            project.getProjectGoals().forEach(goal -> {
                goal.setProject(project);
                if (goal.getPlannings() != null) {
                    goal.getPlannings().forEach(planning -> {
                        planning.setProjectGoal(goal);
                    });
                }
            });
        }
    }
}