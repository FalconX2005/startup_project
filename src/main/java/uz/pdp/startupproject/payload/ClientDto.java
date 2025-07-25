package uz.pdp.startupproject.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.startupproject.enums.RoleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDto {

    private  Long id ;

    private  String firstName ;

    private  String lastName ;

    private String phoneNumber ;

    private Long balance;

    private String username ;

    private String password ;

    private RoleEnum role ;




}
