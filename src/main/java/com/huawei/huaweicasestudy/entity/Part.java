package com.huawei.huaweicasestudy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part")
@SQLDelete(sql = "UPDATE part SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Part extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "part", cascade = CascadeType.DETACH)
    private List<ModelPartQuantity> modelPartQuantities;
}