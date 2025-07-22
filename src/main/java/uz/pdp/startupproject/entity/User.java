package uz.pdp.startupproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;
import uz.pdp.startupproject.enums.RoleEnum;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE attachment SET deleted = true WHERE id = ?")
public class User extends AbsLongEntity {

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

//    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;



}
