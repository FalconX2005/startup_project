package uz.pdp.startupproject.payload;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String username;
    private String code;
    private String newPassword;
}
