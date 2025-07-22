package uz.pdp.startupproject.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Debts extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Client  client;

/*
    @ManyToOne(fetch = FetchType.LAZY)
    private Product  product;
*/

    private Long debtAmount;

    private LocalDate fromDate;

    private LocalDate toDate;

}
