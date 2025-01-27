package com.huawei.huaweicasestudy.repository;

import com.huawei.huaweicasestudy.entity.Project;
import com.huawei.huaweicasestudy.repository.projection.ProjectCalculationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT new com.huawei.huaweicasestudy.repository.projection.ProjectCalculationInfo(" +
            "p.id, pg.goal, m.id, pp.percentage, mpq.part.id, mpq.quantity) " +
            "FROM Project p " +
            "JOIN p.projectGoals pg " +
            "JOIN pg.plannings pp " +
            "JOIN pp.model m " +
            "JOIN m.partQuantities mpq " +
            "JOIN mpq.part part " +
            "WHERE p.id = :projectId AND  pp.isActive = true AND ( pg.month = :month OR pg.month IS null ) ")
    List<ProjectCalculationInfo> findProjectModelPartsByProjectAndMonth(
            @Param("projectId") Long projectId,
            @Param("month") int month);
}
