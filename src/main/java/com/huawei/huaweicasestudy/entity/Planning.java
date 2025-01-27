package com.huawei.huaweicasestudy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planning")
@SQLDelete(sql = "UPDATE planning SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Planning extends BaseEntity {

    private int percentage;

    @ColumnDefault("true")
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_goal_id", nullable = false)
    private ProjectGoal projectGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;
}
