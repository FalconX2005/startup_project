package uz.pdp.startupproject.payload.withoutId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.startupproject.enums.Gender;
import uz.pdp.startupproject.payload.UserDTO;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto implements Serializable {

    @NotBlank(message = "firstName bush bulishi mumkun emas")
    private String firstName;

    @NotBlank(message = "lastName bush bulishi mumkun emas")
    private String lastName;

    @NotBlank(message = "phoneNumber bush bulishi mumkun emas")
    @Pattern(regexp = "\\+998\\d{9}",message = "tel nomer formati notug'ri Masalan: +998901234567")
    private String phoneNumber;

    @NotNull(message = "gender bush bulishi mumkun emas")
    private Gender gender;

    private UserDto user;

    @NotNull(message = "attachmentId bush bulishi mumkun emas")
    private Long attachmentId;



}