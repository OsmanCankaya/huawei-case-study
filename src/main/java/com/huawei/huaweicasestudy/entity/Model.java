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
@Table(name = "model")
@SQLDelete(sql = "UPDATE model SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Model extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ModelPartQuantity> partQuantities = new ArrayList<>();
}
