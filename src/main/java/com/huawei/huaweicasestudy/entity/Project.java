package com.huawei.huaweicasestudy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
@SQLDelete(sql = "UPDATE project SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Project extends BaseEntity {

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanningType planningType;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectGoal> projectGoals = new ArrayList<>();
}