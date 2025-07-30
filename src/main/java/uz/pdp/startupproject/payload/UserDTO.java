package uz.pdp.startupproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import uz.pdp.startupproject.enums.RoleEnum;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {

    private Long id;


    @NotBlank(message = "userName bush bulishi mumkun emas!")
    private String username;

    @NotBlank(message = "password bush bulishi mumkun emas!")
//    @Size(min = 4, message = "password kamida 4 ta belgi bulishi kerak")
    private String password;


//    private String email;

    private RoleEnum role;
}