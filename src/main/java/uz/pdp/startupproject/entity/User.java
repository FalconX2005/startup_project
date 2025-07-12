package uz.pdp.startupproject.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;
import uz.pdp.startupproject.enums.RoleEnum;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User extends AbsLongEntity {

    private String username;

    private String password;

    private String email;

    private RoleEnum role;



}
