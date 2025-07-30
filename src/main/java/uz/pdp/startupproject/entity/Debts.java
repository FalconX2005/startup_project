package uz.pdp.startupproject.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;
import uz.pdp.startupproject.enums.Priority;

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

    @Column(nullable = false)
    private Long debtAmount;

    @Column(nullable = false)
    private LocalDate fromDate;

    @Column(nullable = false)
    private LocalDate toDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
