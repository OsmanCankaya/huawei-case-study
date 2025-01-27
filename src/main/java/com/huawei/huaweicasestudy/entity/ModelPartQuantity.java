package com.huawei.huaweicasestudy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "model_part_quantity")
@SQLDelete(sql = "UPDATE model_part_quantity SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ModelPartQuantity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part part;

    int quantity;

    private boolean deleted = Boolean.FALSE;
}