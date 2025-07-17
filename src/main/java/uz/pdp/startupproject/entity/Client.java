package uz.pdp.startupproject.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Client extends AbsLongEntity {

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private User user;

    private String firstName;

    private String lastName;

    private  String phone;

    private Long balance;

}
