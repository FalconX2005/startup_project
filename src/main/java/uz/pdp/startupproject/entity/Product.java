package uz.pdp.startupproject.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Product extends AbsLongEntity {

    private String name;

    private Long price;


}
