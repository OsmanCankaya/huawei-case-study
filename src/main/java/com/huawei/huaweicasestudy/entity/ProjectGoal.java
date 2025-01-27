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
@Table(name = "project_goal")
@SQLDelete(sql = "UPDATE project_goal SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ProjectGoal extends BaseEntity {

    private int goal;

    @Column(name = "month_column")  // month is a rezerved name
    private Integer month;

    private Integer weekInMonth;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "projectGoal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Planning> plannings = new ArrayList<>();
}
