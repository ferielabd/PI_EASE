package com.example.pi_ease.DAO.Entities;

import com.example.pi_ease.DAO.Entities.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_hisC")
    int Id;

    boolean hasDefaulted;
    int numCreditInquiries;
    @OneToMany(mappedBy = "creditHistory",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Credit> Credits;



    @Override
    public int getId() {
        return 0;
    }
}
