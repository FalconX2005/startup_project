package uz.pdp.startupproject.payload;

import lombok.*;
import uz.pdp.startupproject.entity.Employee;
import uz.pdp.startupproject.enums.Gender;
import uz.pdp.startupproject.enums.RoleEnum;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;

    private UserDTO user;

    private Long attachmentId;


}